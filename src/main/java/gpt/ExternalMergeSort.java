package gpt;

import java.io.*;
import java.util.*;

public class ExternalMergeSort {
    // 内存缓冲区大小（以元素数量为单位）
    private static final int BUFFER_SIZE = 1000;

    public static void main(String[] args) {
        String inputFile = "input.txt";
        String outputFile = "output.txt";

        // 将输入文件分割为有序小块
        splitInputFile(inputFile);

        // 归并有序小块并输出结果到输出文件
        mergeSortedChunks(outputFile);
    }

    // 将输入文件分割为有序小块
    private static void splitInputFile(String inputFile) {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            List<Integer> buffer = new ArrayList<>();
            int chunkIndex = 0;

            String line;
            while ((line = reader.readLine()) != null) {
                buffer.add(Integer.parseInt(line));

                // 当缓冲区达到限制时，对缓冲区进行排序并写入临时文件
                if (buffer.size() >= BUFFER_SIZE) {
                    Collections.sort(buffer);
                    writeChunkToFile(chunkIndex++, buffer);
                    buffer.clear();
                }
            }

            // 处理剩余的数据
            if (!buffer.isEmpty()) {
                Collections.sort(buffer);
                writeChunkToFile(chunkIndex, buffer);
            }

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 将有序小块写入临时文件
    private static void writeChunkToFile(int chunkIndex, List<Integer> chunk) {
        try {
            String fileName = "chunk_" + chunkIndex + ".txt";
            BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));

            for (int num : chunk) {
                writer.write(Integer.toString(num));
                writer.newLine();
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 归并有序小块并输出结果到输出文件
    private static void mergeSortedChunks(String outputFile) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile));

            PriorityQueue<ChunkReader> minHeap = new PriorityQueue<>(Comparator.comparingInt(ChunkReader::getCurrentValue));

            // 初始化堆，将每个有序小块的第一个元素放入堆中
            for (int i = 0; ; i++) {
                String fileName = "chunk_" + i + ".txt";
                File file = new File(fileName);

                if (!file.exists()) {
                    break;
                }

                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line = reader.readLine();
                int value = Integer.parseInt(line);

                minHeap.offer(new ChunkReader(reader, value));
            }

            // 归并操作
            while (!minHeap.isEmpty()) {
                ChunkReader chunkReader = minHeap.poll();
                writer.write(Integer.toString(chunkReader.getCurrentValue()));
                writer.newLine();

                if (chunkReader.moveToNextValue()) {
                    minHeap.offer(chunkReader);
                } else {
                    chunkReader.close();
                }
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 代表一个有序小块的读取器
    private static class ChunkReader {
        private BufferedReader reader;
        private int currentValue;

        public ChunkReader(BufferedReader reader, int currentValue) {
            this.reader = reader;
            this.currentValue = currentValue;
        }

        public int getCurrentValue() {
            return currentValue;
        }

        public boolean moveToNextValue() throws IOException {
            String line = reader.readLine();

            if (line != null) {
                currentValue = Integer.parseInt(line);
                return true;
            } else {
                return false;
            }
        }

        public void close() throws IOException {
            reader.close();
        }
    }
}

