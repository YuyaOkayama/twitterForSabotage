import java.util.*;

public class jTweet extends Thread{
	public static void main (String[] args) { 
		SubThread read = new SubThread();
		read.start();
		
		while(true){
			try{
				System.out.println("ツイートを入力してください");
				Scanner scan = new Scanner(System.in);

				String latestStatus = scan.nextLine();

				if (latestStatus == "exit"){
						break;
				} else {
				twitter4j.Twitter twitter = twitter4j.TwitterFactory.getSingleton();
				twitter4j.Status status = twitter.updateStatus(latestStatus);
				System.out.println(status.getText() + "ってツイートしたよ");
				//System.out.println("ツイートしたよ");
				}
			}catch(twitter4j.TwitterException e){
			}
		}
	}
}

class SubThread extends Thread{
	public void run() { 
		long id[] = new long[20];
		for (int i = 0; i < 20; i++){
			id[i] = 0;
		}
	
		while(true){
			try{
				int n = 0;
				twitter4j.Twitter twitter = twitter4j.TwitterFactory.getSingleton();
				List<twitter4j.Status> statuses = twitter.getHomeTimeline();

				long tmp[] = new long[20];
				
				for (twitter4j.Status status : statuses){
					if (!Arrays.asList(id).contains(status.getId())){
						System.out.println(status.getText());
					}
					tmp[n] = status.getId();
					n++;
				}
				
				id = tmp.clone();

			}catch(twitter4j.TwitterException e){
			}

			try{
 				 Thread.sleep(60 * 1000);
			}catch (InterruptedException e){
			}
		}
	}
}