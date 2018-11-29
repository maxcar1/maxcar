
public class Test {
	public static void main(String[] args) {
		String a = "国4(国5)";
		String NewStr=a.substring(a.indexOf("(")+1, a.lastIndexOf(")"));
	System.out.println(	NewStr);
	}
}
