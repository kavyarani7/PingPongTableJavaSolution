import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Iterator;
import java.util.stream.IntStream;
import java.util.Date;

class Main {
	private static int[] playersAtEachHour = new int[10];
	public static void main(String[] args) {
  		try {
			Iterator<String> lines = Files.lines(Paths.get("./PingPongInput.txt"))
		    .map(s -> s.trim())
		    .filter(s -> !s.isEmpty())
		    .iterator();
			
			while(lines.hasNext()) {
				Arrays.fill(playersAtEachHour, 0);
				int players = Integer.parseInt(lines.next());
				IntStream.rangeClosed(1, players)
				.forEach(i -> getRange(lines.next().split(" ")));
				long singles = Arrays.stream(playersAtEachHour)
				.filter(h -> h > 1 && h <= 3)
				.count();
				long doubles = Arrays.stream(playersAtEachHour)
				.filter(h -> h > 3)
				.count();
				System.out.println((singles) + " " + (doubles));
			}	
		} catch (IOException e) {
			System.out.println("Exception reading the input file");
			e.printStackTrace();
		}
	}

	private static void getRange(String[] hours) {
		int start = Integer.parseInt(hours[0]);
		int end = Integer.parseInt(hours[1]);
		IntStream.range(start, end).forEach(n -> playersAtEachHour[n-9]++);;
	}
}
