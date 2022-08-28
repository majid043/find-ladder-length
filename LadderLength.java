import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.stream.Collectors;

public class LadderLength {
	int ladderLength(String beginWord, String endWord, List<String> wordList) {
		HashSet<String> hs=new HashSet<>(wordList);
		if(!hs.contains(endWord))
		{
			return 0;
		}
		int steps=1;
		Queue<String> q=new LinkedList<>();
		q.add(beginWord);
		while(!q.isEmpty())
		{
			int count=q.size();
			for(int i=0;i<count;i++)
			{
				String curr=q.poll();
				char[] a=curr.toCharArray();
				for(int j=0;j<a.length;j++)
				{
					char temp=a[j];
					for(char c='a';c<='z';c++)
					{
						if(a[j]==c) continue;
						a[j]=c;
						String test=new String(a);
						if(test.equals(endWord)) return steps+1;
						if(hs.contains(test))
						{
							q.add(test);
							hs.remove(test);
						}
					}
					a[j]=temp;
				}
			}
			steps++;
		}
		return 0;
	}

	int[] solution(int[] a) {
		int n = a.length;
		Map<Integer,Integer> mapInt = new HashMap<Integer,Integer>();
		for(int i=0;i<n;i++){
			String str = String.valueOf(a[i]);
			char c[] = str.toCharArray();
			for (int j = 0; j<c.length; j++) {
				int chari =Character.getNumericValue(c[j]);	        	
				if (null == mapInt.get(chari)) {
					mapInt.put(chari, 1);
				} else {
					Integer temp = mapInt.get(chari);
					mapInt.put(chari, temp+1);
				}
			}
		}	    
		Integer max=mapInt.entrySet().stream().max(Map.Entry.comparingByValue()).get().getKey();
		List<Integer> list = mapInt.entrySet().stream().filter(e->e.getValue() == max).map(Map.Entry::getKey).collect(Collectors.toList());
		return list.stream().mapToInt(Integer::intValue).toArray();
	}

	public static void main(String[] args) {
		LadderLength sol = new LadderLength();
		List<String> wordList = new ArrayList<String>();
		wordList.add("hot");
		wordList.add("dot");
		wordList.add("dog");
		wordList.add("lot");
		wordList.add("log");
		wordList.add("cog");
		System.out.println(sol.ladderLength("hit", "cog", wordList));

		int array[] = {25,2,3,57,38,41};
		System.out.println(Arrays.toString(sol.solution(array)));
	}
}