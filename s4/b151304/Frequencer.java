package s4.b151304; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID.
import java.lang.*;
import java.util.ArrayList;
import s4.specification.*;

/*
interface FrequencerInterface {     // このインタフェースは、周波数カウンタの設計を提供します。
    void setTarget(byte[]  target); // 検索するデータを設定します。
    void setSpace(byte[]  space);  // 検索対象となるデータを設定します。
    int frequency(); //TARGETが設定されていないか、TARGETの長さがゼロの場合は-1を返します。
                    //それ以外の場合は、SPACEが設定されていないか、またはスペースの長さがゼロの場合は0を返します。
                    さもなければ、スペースのTARGETの頻度を得る。
    int subByteFrequency(int start, int end);
    // target [start]、target [start + 1]、...、target [end-1]のターゲットのサブバイトの頻度を取得します。
    // STARTまたはENDの値が正しくない場合の動作は未定義です。
*/

public class Frequencer implements FrequencerInterface {

  byte [] myTarget;
  byte [] mySpace;
  boolean targetReady = false;
  boolean spaceReady = false;

  int [] suffixArray;

  /*
  変数 "suffixArray"は、mySpaceのsuffixがソートされた配列です。
  各suffixは、mySpaceの開始位置である整数で表されます。
  suffixArrayを出力するコードは次のとおりです。
  */
  private void printSuffixArray(int[] array) {
    if(spaceReady) {
      for(int i = 0; i < array.length; i++) {
        // System.out.print(i+":"+array[i]+":"); // 検査コード
        for(int j = array[i]; j < mySpace.length; j++) {
          System.out.write(mySpace[j]);
        }
        System.out.write('\n');
      }
      System.out.write('\n');
    }
  }

  /*
  2つのsuffixを辞書順で比較します。
  i、jはsuffix_i、suffix_jを示す。
  1ならば入れ替える。-1ならそのまま。
  suffixArrayの作成に使用する必要があります。
  _:32, H:72, 105:i, 111:o
  <辞書順の例>
  "i"    < "o"       : コードで比較する
  "Hi"   < "Ho "     ; headが同じなら、次の要素を比較する
  "Ho"   < "Ho "     ; suffixが同一であれば、長い文字列が大きい
  */
  private int suffixCompare(int i, int j) {
    for(int a = 0; a < mySpace.length - i && a < mySpace.length - j; a++) {
      if(mySpace[i+a] > mySpace[j+a]) return 1;
      else if(mySpace[i+a] < mySpace[j+a]) return -1;
    }
    if(i < j) return 1;
    if(i > j) return -1;
    return 0;
  }

  /*
  すべてのsuffixをsuffixArrayに入れます。 各suffixは整数で表されます。
  <"Hi Ho Hi Ho"の例>
  0: Hi Ho
  1: Ho
  2: Ho Hi Ho
  3:Hi Ho
  4:Hi Ho Hi Ho
  5:Ho
  6:Ho Hi Ho
  7:i Ho
  8:i Ho Hi Ho
  9:o
  A:o Hi Ho
  */
  public void setSpace(byte []space) {
    mySpace = space;
    if(mySpace.length > 0) spaceReady = true;
    suffixArray = new int[space.length];

    for(int i = 0; i< space.length; i++) {
      suffixArray[i] = i;
    }
    // printSuffixArray(suffixArray); // 検査コード
    // System.out.println("-------------------"); // 検査コード

    // bubbleSort(suffixArray);
    mergeSort(suffixArray);
    // printSuffixArray(suffixArray); // 検査コード
  }

  /* バブルソート */
  public void bubbleSort(int[] array) {
    int temp;
    for(int i = 0; i < array.length - 1; i++) {
      for(int j = i + 1; j < array.length; j++) {
        int flag = suffixCompare(array[i],array[j]);
        if(flag == 1) { // iの方が大きかったら入れ替える
          temp = array[i];
          array[i] = array[j];
          array[j] = temp;
          // printSuffixArray(array); // 検査コード
          // System.out.println("-------------------"); // 検査コード
        }
      }
    }
  }

  /* マージソート */
  public void mergeSort(int[] array) {

    if (array.length > 1 && array.length < 5) {
      bubbleSort(array);
      return;
    }

    if(array.length > 1) {
      int[] left = new int[array.length / 2];
      int[] right = new int[array.length - left.length];

      for(int i = 0; i < left.length; i++) left[i] = array[i];
      for(int i = 0; i < right.length; i++) right[i] = array[left.length + i];

      mergeSort(left);
      // printSuffixArray(left); // 検査コード
      mergeSort(right);
      // printSuffixArray(right); // 検査コード
      merge(left, right, array);

    }
  }

  /*  マージ：2つの配列left[]とright[]を併合してarray[]を作ります。 */
  public void merge(int[] left, int[] right, int[] array) {
    int indexL = 0, indexR = 0, index = 0;

    while (indexL < left.length && indexR < right.length) {
      int flag = suffixCompare(left[indexL], right[indexR]);
      if (flag == -1) { // iとjで小さい方をarrayに入れる
        array[index++] = left[indexL++];
      }
      else{
        array[index++] = right[indexR++];
      }
    }
    while (indexL < left.length) { array[index++] = left[indexL++]; }
    while (indexR < right.length) { array[index++] = right[indexR++]; }

    // printSuffixArray(suffixArray); // 検査コード
    // System.out.println("-------------------"); // 検査コード
  }

  /*
  suffix_iとtarget_start_endを辞書順で長さの制限付きで比較する。
  suffixの適切なインデックスを検索するために使用する必要があります。
  <検索の例>
  suffix     target
  "o"    >   "i"   // 1
  "o"    <   "z"   // -1
  "o"    =   "o"   // 0
  "o"    <   "oo"  // -1
  "Ho"   >   "Hi"  // 1
  "Ho"   <   "Hz"  // -1
  "Ho"   =   "Ho"  // 0
  "Ho"   <   "Ho " // "Ho "はsuffix "Ho"のheadにない
  "Ho"   =   "H"   // "H"はsuffix "Ho"のheadにあります
  */
  private int targetCompare(int i, int start, int end) {

    if (i < 0) return -1;
    if (i >  mySpace.length - 1) return 1;

    int si = suffixArray[i];
    int s = 0;
    if (si > s) s = si;
    int n = mySpace.length - si;

    if (n > end - start) n = end - start;

    for (int a = 0; a < n; a++) {
      if (mySpace[si + a] > myTarget[a]) return 1;
      if (mySpace[si + a] < myTarget[a]) return -1;
    }

    if (n < end - start) return -1;

    return 0;
  }

  /*
  この関数は、subBytes以上の最初のsuffixのインデックスを返します。
  For "Ho", it will return 5 for "Hi Ho Hi Ho".
  For "Ho ", it will return 6 for "Hi Ho Hi Ho".
  */
  private int subByteStartIndex(int start, int end) {
    int pLeft = 0;
    int pRight = mySpace.length - 1;

    /* 2分探索 */
    do {
      int center = (pLeft + pRight) / 2;

      if (targetCompare(center, start, end) == 0 && targetCompare(center - 1, start, end) == -1 ) {
        return center;
      }
      else if (targetCompare(center, start, end) == -1) {
        pLeft = center + 1; // 真ん中の一つ右側を左端とする
      }
      else {
        pRight = center - 1; // 真ん中の一つ左側を右端とする
      }
    } while (pLeft <= pRight);

    /* 線型探索 */
    // for (int i = 0; i < mySpace.length; i++) {
    //   if (targetCompare(suffixArray[i], start, end) == 0) return i;
    // }
    return suffixArray.length;
  }

  /*
   この関数は、subBytesより大きい最初のsuffixの次のインデックスを返します。
  For "Ho", it will return 7 for "Hi Ho Hi Ho".
  For "Ho ", it will return 7 for "Hi Ho Hi Ho".
  */
  private int subByteEndIndex(int start, int end) {
    int pLeft = 0;
    int pRight = mySpace.length - 1;

    /* ２分探索 */
    do {
      int center = (pLeft + pRight) / 2;

      if (targetCompare(center, start, end) == 0 && targetCompare(center + 1, start, end) == 1) {
          return center + 1;
      }
      else if (targetCompare(center, start, end) == 1) {
        pRight = center - 1; // 真ん中の一つ左側を右端とする
      }
      else {
        pLeft = center + 1; // 真ん中の一つ右側を左端とする
      }
    } while (pLeft <= pRight);

    /* 線型探索 */
    // for (int i = 0; i < mySpace.length - 1; i++) {
    //   if (targetCompare(suffixArray[i], start, end) == 0 && targetCompare(suffixArray[i + 1], start, end) != 0) return i + 1;
    // }
    return suffixArray.length;
  }

  public int subByteFrequency(int start, int end) {
    /* この方法は遅いが、以下のように定義することができる。
    int spaceLength = mySpace.length;
    int count = 0;
    for(int offset = 0; offset< spaceLength - (end - start); offset++) {
      boolean abort = false;
      for(int i = 0; i< (end - start); i++) {
        if(myTarget[start+i] != mySpace[offset+i]) { abort = true; break; }
      }
      if(abort == false) { count++; }
    }
    */

    // setなしでsubByteFrequencyを呼ばれた場合の例外処理
    if(targetReady == false) return -1;
    if(spaceReady == false) return 0;

    int first = subByteStartIndex(start,end);
    int last1 = subByteEndIndex(start, end);

    // // 検査コード
    // for(int k = start; k < end; k++) { System.out.write(myTarget[k]); }
    // System.out.printf(": first=%d last1=%d\n", first, last1);

    return last1 - first;
  }

  public void setTarget(byte [] target) {
    myTarget = target;
    if(myTarget.length > 0) targetReady = true;
  }

  public int frequency() {
    if(targetReady == false) return -1;
    if(spaceReady == false) return 0;
    return subByteFrequency(0, myTarget.length);
  }

  public static void main(String[] args) { Frequencer frequencerObject;
    try {
      frequencerObject = new Frequencer();
      frequencerObject.setSpace("Hi Ho Hi Ho".getBytes());
      frequencerObject.setTarget("o Hi Ho".getBytes());
      int result = frequencerObject.frequency();
      System.out.print("Freq = " + result + " ");
      if(1 == result) { System.out.println("OK"); }
      else {System.out.println("WRONG"); }
    }
    catch(Exception e) {
      System.out.println("STOP\n"+e);
    }
  }
}
