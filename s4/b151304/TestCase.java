package s4.b151304; // Please modify to s4.Bnnnnnn, where nnnnnn is your student ID.
import java.lang.*;
import s4.specification.*;

/*
interface FrequencerInterface {     // This interface provides the design for frequency counter.
    void setTarget(byte[]  target); // set the data to search.
    void setSpace(byte[]  space);  // set the data to be searched target from.
    int frequency(); //It return -1, when TARGET is not set or TARGET's length is zero
                    //Otherwise, it return 0, when SPACE is not set or Space's length is zero
                    //Otherwise, get the frequency of TAGET in SPACE
    int subByteFrequency(int start, int end);
    // get the frequency of subByte of taget, i.e target[start], taget[start+1], ... , target[end-1].
    // For the incorrect value of START or END, the behavior is undefined.
}
*/

/*
package s4.specification;
public interface InformationEstimatorInterface{
    void setTarget(byte target[]); // set the data for computing the information quantities
    void setSpace(byte space[]); // set data for sample space to computer probability
    double estimation(); // It returns 0.0 when the target is not set or Target's length is zero;
// It returns Double.MAX_VALUE, when the true value is infinite, or space is not set.
// The behavior is undefined, if the true value is finete but larger than Double.MAX_VALUE.
// Note that this happens only when the space is unreasonably large. We will encounter other problem anyway.
// Otherwise, estimation of information quantity,
}
*/


public class TestCase {
  public static void main(String[] args) {

    //default
    try {
      FrequencerInterface  myObject;
      int freq;
      System.out.println("checking s4.b151304.Frequencer");
      myObject = new s4.b151304.Frequencer();
      myObject.setSpace("Hi Ho Hi Ho".getBytes());
      myObject.setTarget("H".getBytes());
      freq = myObject.frequency();
      System.out.print("\"H\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
      if(4 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
    }
    catch(Exception e) {
      System.out.println("Exception occurred: STOP");
    }

    //ターゲットの文字列Hoの場合
    try {
      FrequencerInterface  myObject;
      int freq;
      System.out.println("checking s4.b151304.Frequencer");
      myObject = new s4.b151304.Frequencer();
      myObject.setSpace("Hi Ho Hi Ho".getBytes());
      myObject.setTarget("Ho".getBytes());
      freq = myObject.frequency();
      System.out.print("\"Ho\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
      if(2 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
    }
    catch(Exception e) {
      System.out.println("Exception occurred: STOP");
    }

    //ターゲットの文字列oの場合
    try {
      FrequencerInterface  myObject;
      int freq;
      System.out.println("checking s4.b151304.Frequencer");
      myObject = new s4.b151304.Frequencer();
      myObject.setSpace("Hi Ho Hi Ho".getBytes());
      myObject.setTarget("o".getBytes());
      freq = myObject.frequency();
      System.out.print("\"o\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
      if(2 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
    }
    catch(Exception e) {
      System.out.println("Exception occurred: STOP");
    }

    //ターゲットの文字列o Hi Hoの場合
    try {
      FrequencerInterface  myObject;
      int freq;
      System.out.println("checking s4.b151304.Frequencer");
      myObject = new s4.b151304.Frequencer();
      myObject.setSpace("Hi Ho Hi Ho".getBytes());
      myObject.setTarget("o Hi Ho".getBytes());
      freq = myObject.frequency();
      System.out.print("\"o Hi Ho\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
      if(1 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
    }
    catch(Exception e) {
      System.out.println("Exception occurred: STOP");
    }

    //ターゲットの文字列z(最後の文字)の場合
    try {
      FrequencerInterface  myObject;
      int freq;
      System.out.println("checking s4.b151304.Frequencer");
      myObject = new s4.b151304.Frequencer();
      myObject.setSpace("Hi Ho Hi Ho z".getBytes());
      myObject.setTarget("z".getBytes());
      freq = myObject.frequency();
      System.out.print("\"z\" in \"Hi Ho Hi Ho z\" appears "+freq+" times. ");
      if(1 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
    }
    catch(Exception e) {
      System.out.println("Exception occurred: STOP");
    }

    //ターゲットの文字列空白の場合
    try {
      FrequencerInterface  myObject;
      int freq;
      System.out.println("checking s4.b151304.Frequencer");
      myObject = new s4.b151304.Frequencer();
      myObject.setSpace("Hi Ho Hi Ho".getBytes());
      myObject.setTarget(" ".getBytes());
      freq = myObject.frequency();
      System.out.print("\" \" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
      if(3 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
    }
    catch(Exception e) {
      System.out.println("Exception occurred: STOP");
    }


    //ターゲットの長さが0の場合
    try {
      FrequencerInterface  myObject;
      int freq;
      System.out.println("checking s4.b151304.Frequencer");
      myObject = new s4.b151304.Frequencer();
      myObject.setSpace("Hi Ho Hi Ho".getBytes());
      myObject.setTarget("".getBytes());
      freq = myObject.frequency();
      System.out.print("\"\" in \"Hi Ho Hi Ho\" appears "+freq+" times. ");
      if(-1 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
    }
    catch(Exception e) {
      System.out.println("Exception occurred: STOP");
    }

    //スペースの長さが0の場合
    try {
      FrequencerInterface  myObject;
      int freq;
      System.out.println("checking s4.b151304.Frequencer");
      myObject = new s4.b151304.Frequencer();
      myObject.setSpace("".getBytes());
      myObject.setTarget("H".getBytes());
      freq = myObject.frequency();
      System.out.print("\"H\" in \"\" appears "+freq+" times. ");
      if(0 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
    }
    catch(Exception e) {
      System.out.println("Exception occurred: STOP");
    }

    // //space is 100bite. target is 10bite
    // try {
    //   FrequencerInterface  myObject;
    //   int freq;
    //   String strSpace =
    //   "The bento has become a medium of communication, a way for the creator" +
    //   "to express their feelings for the person who will eat it.";
    //
    //   String strTarget = "communication";
    //
    //   System.out.println("checking s4.b151304.Frequencer");
    //   myObject = new s4.b151304.Frequencer();
    //   myObject.setSpace(strSpace.getBytes());
    //   myObject.setTarget(strTarget.getBytes());
    //   freq = myObject.frequency();
    //   System.out.print(strSpace+" in "+strTarget+" appears "+freq+" times. ");
    //   if(4 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
    // }
    // catch(Exception e) {
    //   System.out.println("Exception occurred: STOP");
    // }

    // //space is 100bite. target is 10bite
    // try {
    //   FrequencerInterface  myObject;
    //   int freq;
    //   String strSpace =
    //   "The bento has become a medium of communication, a way for the creator" +
    //   "to express their feelings for the person who will eat it.";
    //
    //   String strTarget = "communication";
    //
    //   System.out.println("checking s4.b151304.Frequencer");
    //   myObject = new s4.b151304.Frequencer();
    //   myObject.setSpace(strSpace.getBytes());
    //   myObject.setTarget(strTarget.getBytes());
    //   freq = myObject.frequency();
    //   System.out.print(strSpace+" in "+strTarget+" appears "+freq+" times. ");
    //   if(4 == freq) { System.out.println("OK"); } else {System.out.println("WRONG"); }
    // }
    // catch(Exception e) {
    //   System.out.println("Exception occurred: STOP");
    // }

    // default
    try {
      InformationEstimatorInterface myObject;
      double value;
      System.out.println("checking s4.b151304.InformationEstimator");
      myObject = new s4.b151304.InformationEstimator();
      myObject.setSpace("3210321001230123".getBytes());
      myObject.setTarget("0".getBytes());
      value = myObject.estimation();
      System.out.println(">0 "+value);
      myObject.setTarget("01".getBytes());
      value = myObject.estimation();
      System.out.println(">01 "+value);
      myObject.setTarget("0123".getBytes());
      value = myObject.estimation();
      System.out.println(">0123 "+value);
      myObject.setTarget("00".getBytes());
      value = myObject.estimation();
      System.out.println(">00 "+value);
      myObject.setTarget("23".getBytes());
      value = myObject.estimation();
      System.out.println(">23 "+value);
      myObject.setTarget("3".getBytes());
      value = myObject.estimation();
      System.out.println(">3 "+value);

    }
    catch(Exception e) {
      System.out.println("Exception occurred: STOP\n"+e);
    }

    // //ターゲットが長さが0の場合
    // try {
    //   InformationEstimatorInterface myObject;
    //   double value;
    //   System.out.println("checking s4.b151304.InformationEstimator");
    //   myObject = new s4.b151304.InformationEstimator();
    //   myObject.setSpace("300".getBytes());
    //   myObject.setTarget("".getBytes());
    //   value = myObject.estimation();
    //   System.out.print(value + " :");
    //   if(0.0 == value) { System.out.println("OK"); } else {System.out.println("WRONG"); }
    // }
    // catch(Exception e) {
    //   System.out.println("Exception occurred: STOP");
    // }
  }
}
