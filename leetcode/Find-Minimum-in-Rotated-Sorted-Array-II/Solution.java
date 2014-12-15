public class Solution {
    public int findMin(int[] num) {
        if(num.length==1)return num[0];
        
        return findMin(num,0,num.length);
    }
    
    public int findMin(int[] num, int startIndex, int length){
        if(length==1)return num[startIndex];
        if(length==2)return num[startIndex]>num[startIndex+1]?num[startIndex+1]:num[startIndex];
        
        int mid = (length-1)/2+startIndex;
        int endIndex=startIndex+length-1;
        if(num[mid]>num[endIndex]&&num[mid]>=num[startIndex]){
            return findMin(num, mid, endIndex-mid+1);//right
        } else if (num[mid]==num[endIndex]&&num[mid]>num[startIndex]){
            return findMin(num, startIndex, mid-startIndex+1); //left
        } else if(num[mid]<=num[endIndex]&&num[mid]<num[startIndex]) {
            return findMin(num, startIndex, mid-startIndex+1);//left
        } else if(num[mid]<num[endIndex]&&num[mid]==num[startIndex]) {
            return findMin(num, mid, endIndex-mid+1);//right
        } else{
            int left = findMin(num, startIndex, mid-startIndex+1);
            int right = findMin(num, mid, endIndex-mid+1);
            return left<right?left:right;
        }
        
    }
    
    
    
}
