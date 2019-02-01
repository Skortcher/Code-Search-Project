import java.util.HashMap;
import java.util.HashSet;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;


public class CodeToVector{
  public static void main(String[] args) throws Exception{
    HashMap<String, Integer> wordFreqMap1 = new HashMap<>();
    HashMap<String, Integer> wordFreqMap2 = new HashMap<>();
    HashSet<String> wordsSeen = new HashSet<>();
    ArrayList<Double> vector = new ArrayList<>();


    Scanner doc1 = new Scanner(new File("Document1"));
    Scanner doc2 = new Scanner(new File("Document2"));

    int total1 = findWordFreq(wordsSeen, wordFreqMap1, doc1);
    int total2 = findWordFreq(wordsSeen, wordFreqMap2, doc2);

    for(String word: wordsSeen){
      double tf;
      if(wordFreqMap1.containsKey(word))
        tf = wordFreqMap1.get(word) / (double) total1;
      else tf = 0;
      int numOfDocsThatContainWord = 0;
      if(wordFreqMap1.containsKey(word))
        numOfDocsThatContainWord++;
      if(wordFreqMap2.containsKey(word))
        numOfDocsThatContainWord++;
      double idf = Math.log(2.0/numOfDocsThatContainWord);
      vector.add(tf*idf);
    }

    System.out.println(vector);
  }

  public static int findWordFreq(HashSet<String> wordsSeen, HashMap<String, Integer> wordFreqMap, Scanner doc){
    int total=0;
    while(doc.hasNext()){
      String word = doc.next();
      if(wordFreqMap.containsKey(word)){
        wordFreqMap.put(word, wordFreqMap.get(word)+1);
      }
      else{
        wordFreqMap.put(word, 1);
        wordsSeen.add(word);
      }
      total += 1;
    }
    return total;
  }
}
