public class MultiSourceBFS{
  Queue<int[]> queue = new LinkedList<>();
  boolean[][] visited = new boolean[n][m];
  
  // Optional: Add all starting points into the queue
  queue.offer(new int[]{startRow, startCol});
  visited[startRow][startCol] = true;
  
  int[][] dirs = {{0,1},{1,0},{0,-1},{-1,0}};
  int steps = 0;
  
  while (!queue.isEmpty()) {
      int size = queue.size();
  
      for (int i = 0; i < size; i++) {
          int[] cell = queue.poll();
          int row = cell[0], col = cell[1];
  
          for (int[] dir : dirs) {
              int newRow = row + dir[0];
              int newCol = col + dir[1];
  
              if (isValid(newRow, newCol, grid) && !visited[newRow][newCol]) {
                  visited[newRow][newCol] = true;
                  grid[newRow][newCol] = updatedValue; // transformation
                  queue.offer(new int[]{newRow, newCol});
              }
          }
      }
      steps++; // optional — for tracking time or distance
  }
}
