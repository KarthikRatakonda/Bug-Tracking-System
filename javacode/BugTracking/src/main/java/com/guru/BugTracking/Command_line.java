package com.guru.BugTracking;



import java.io.IOException;
import java.util.Scanner;

public class Command_line 
{

	private static Scanner sc;
	private static String str[];
	
	
	public static void main(String[] args) throws IOException
	{
		int  check=0;

		System.out.println("Give the String Length");
		sc = new Scanner(System.in);
		int a=sc.nextInt();
		args=new String[a];
		String s[]=new String[a];
		str=new String[a];
		System.out.println("lenght of an array given by usr"+args.length);
		for(int i=0;i<args.length;i++)
		{
			args[i]=sc.next();
		}
		
		for(int j=0;j<args.length;j++) 
		{
			for(int k=0;k<args.length;k++)
			{
				if(j==k) {
					
				}else {
				boolean f=args[j].equalsIgnoreCase(args[k]);//boolean ture or false 1 or 0
				if(f==true) {
					check=1;
				}else{
					check=0;
				}
				
				switch(check) 
				{
				case 1:
				{
					
					s[j]=args[j];
					
					
					break;
				}
				default:{
					//System.out.println("No Matched Array String:");
				}

				}
			}
	}}
		
		my_method(args);
		for(int z=0;z<s.length;z++) {
			if(s[z]==null) {
				
			}else {
				String temp=s[z];
				for(int j=1;j<str.length-1;j++)
					if(temp.equalsIgnoreCase(s[z])) {
						
					}else {
						str[z]=temp;
					}
				
				
				
			//System.out.println("Matched Array String:"+s[z]);
			}
		}
		System.out.println(str);
	}
	
	
	public static  void my_method(String tempString[]) {
	//	String[] tempString = new String[] {"john","john","sita","ram","ram"};
		System.out.println("array length is"+tempString.length);
        String[] tempArray = new String[tempString.length];
        int occurrence;
        int indexLocation = 0;
 
        for (int i = 0; i < tempString.length; i++)
        {
            occurrence = 0;
             
        for (int j = 0; j < tempString.length; j++)
                if (tempString[i].equals(tempString[j])) {
                    occurrence++;
                    if(occurrence>1){
tempString[i] = j+""+i;  
                    }
                }
             
            try{
            Integer.parseInt(tempString[i]);
            }
            catch(Exception e){
                tempArray[indexLocation] = tempString[i];
                indexLocation++;
            }
                 
        }
 
         
         for(String x:tempArray){ System.out.println(x); }
}
	}
	

	
	
	


