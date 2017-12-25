	/**
	 * 计算是否有复数
	 * @param arr
	 * @param target
	 * @return
	 */
	public static int [] indices(int [] arr, int target){
		int i,j;
		for(i = 0; i < arr.length; i++){
			for(j = i + 1; j < arr.length; j++){
				if(arr[i] + arr[j] == target){
					return new int [] {arr[i], arr[j]};
				}
			}
		}
		return null;
	}

	/**
	 * 回文串
	 * @param s
	 * @return
	 */
	public static String get(String s){
		int j = 0;
		for(int i = s.length() - 1; i >= 0; i--){
			if(s.charAt(i) == s.charAt(j)){
				j += 1;
			}
		}
		if(j == s.length()){
			return s;
		}
		String end = s.substring(j);
		String front = new StringBuilder(end).reverse().toString();
		String midl = get(s.substring(0, j));
		return front + midl + end;
	}
	
	 public static int lengthOfLongestSubstring(String s) {
	     int re = 0;
		 for(int i = 0; i < s.length(); i++){
			 for(int j = i + 1; j <= s.length(); j++){
				 if(allU(s, i, j)){
					 re = Math.max(re, j - i);
				 }
			 }
		 }
		 return re;
	 }
	 public static boolean allU(String s, int start, int end){
		 Set<Character> set = new HashSet<Character>();
		 for(int i = start; i < end; i++){
			 Character c = s.charAt(i);
			 if(set.contains(c)) return false;
			 set.add(c);
		 }
		 return true;
	 }