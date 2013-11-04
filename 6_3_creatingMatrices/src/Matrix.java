import java.util.*;

public class Matrix {
  private int[][] m;

  private Matrix (int d1, int d2) {
    m = new int[d1][d2]; 
    for (int i = 0; i < d1; ++i) 
      for (int j = 0; j < d2; ++j)
        m[i][j] = 1;
  }
  
  private void setElement(int d1, int d2, int v) {
    if (d1 < m.length && d2 < m[m.length - 1].length)
      m[d1][d2] = v;
  }

  // a method setRow(int,String) that modifies one whole row of the array, given its position as an integer and the list of numbers as a String like “1,2,3”. The method must check that the index is valid and the numbers are correct (i.e. if the array has three columns, the String contains three numbers). If the index or the String is invalid, the method will do nothing.
  private void setRow(int rowNum, String rowVals) {
    // setRow(1, "6,5,4,343,2");
    if (rowNum >= 0 && rowNum < m.length) {
      String[] rowValStrings = rowVals.split(",");
      if (rowValStrings.length == m[rowNum].length) {
        for (int i = 0; i < m[rowNum].length; ++i) {
          m[rowNum][i] = (Integer.parseInt(rowValStrings[i]));
        }
      } 
    }
  }

  private void launch() {
    // int d1 = 3, d2 = 5, val = 99;
    // setElement(d1, d2, val);
    setRow(1, "6,5,4,343,2");
    System.out.println(m[1][3]);
  }

  public static void main(String[] args) {
    int d1 = 4, d2 = 5;
    Matrix m = new Matrix(d1, d2);
    m.launch();
  }

}