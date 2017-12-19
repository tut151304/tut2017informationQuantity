package s4.b151304; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID.
import java.lang.*;
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


public class Frequencer implements FrequencerInterface{
  // Code to Test, *warning: This code  contains intentional problem*
  byte [] myTarget;
  byte [] mySpace;
  public void setTarget(byte [] target) { myTarget = target;}
  public void setSpace(byte []space) { mySpace = space; }
  public int frequency() {
    int targetLength = myTarget.length;
    int spaceLength = mySpace.length;
    int count = 0;
    for(int start = 0; start<spaceLength; start++) { // ターゲットが0文字のとき-1を返すように修正しました
      boolean abort = false;
      if(targetLength == 0) { return -1; }
      for(int i = 0; i<targetLength; i++) {
        if(myTarget[i] != mySpace[start+i]) { abort = true; break; }
      }
      if(abort == false) { count++; }
    }
    return count;
  }

  // I know that here is a potential problem in the declaration.
  public int subByteFrequency(int start, int length) {
    // Not yet, but it is not currently used by anyone.
    return -1;
  }

  public static void main(String[] args) {
    Frequencer myObject;
    int freq;
    try {
      System.out.println("checking my Frequencer");
      myObject = new Frequencer();
      myObject.setSpace("Hi Ho Hi Ho".getBytes());
      myObject.setTarget("H".getBytes());
      freq = myObject.frequency();
      System.out.print("\"H\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
      if(4 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
    }
    catch(Exception e) {
      System.out.println("Exception occurred: STOP");
    }
  }
}
