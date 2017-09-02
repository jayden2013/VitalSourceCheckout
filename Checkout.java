import java.awt.AWTException;
import java.awt.Robot;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.concurrent.TimeUnit;

/**
 * Automates saving VitalSource ebooks as PDFs.
 * @author Jayden Weaver
 *
 */
public class Checkout {

	public static void main(String args[]){

		int pageNumber = Integer.parseInt(args[0]);
		int totalPages = Integer.parseInt(args[1]);
		int pagesToPrint = Integer.parseInt(args[2]);
		int pdfNumber = 0;
		Clipboard clipboard;
		StringSelection stringSelection;
		try {
			System.out.println("Select the VitalSource Bookshelf ebook Window within the next six seconds.");
			TimeUnit.SECONDS.sleep(6);
		} catch (InterruptedException e1) {
			e1.printStackTrace();
		}

		try {

			while(pageNumber < totalPages){
				//Prepare page number
				stringSelection = new StringSelection("" + pageNumber);

				//Update page number
				pageNumber += pagesToPrint;

				//Add page number to clipboard to CTRL + V later.
				clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, stringSelection);

				Robot robot = new Robot(); //beep boop

				//CTRL + P
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_P);
				robot.keyRelease(KeyEvent.VK_CONTROL);
				robot.keyRelease(KeyEvent.VK_P);

				//Tab to the page box and delete everything.
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);
				robot.keyPress(KeyEvent.VK_DELETE);
				robot.keyRelease(KeyEvent.VK_DELETE);
				robot.keyPress(KeyEvent.VK_DELETE);
				robot.keyRelease(KeyEvent.VK_DELETE);
				robot.keyPress(KeyEvent.VK_DELETE);
				robot.keyRelease(KeyEvent.VK_DELETE);
				robot.keyPress(KeyEvent.VK_DELETE);
				robot.keyRelease(KeyEvent.VK_DELETE);

				//Enter page number with CTRL + V
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_CONTROL);

				//Tab to update other box
				robot.keyPress(KeyEvent.VK_TAB);
				robot.keyRelease(KeyEvent.VK_TAB);

				//Hit enter on all the boxes
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);
				TimeUnit.SECONDS.sleep(2);
				robot.keyPress(KeyEvent.VK_ENTER);
				robot.keyRelease(KeyEvent.VK_ENTER);

				//Paste PDF number to save without overwriting
				stringSelection = new StringSelection("" + pdfNumber);
				clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
				clipboard.setContents(stringSelection, stringSelection);
				TimeUnit.SECONDS.wait(2);
				robot.keyPress(KeyEvent.VK_CONTROL);
				robot.keyPress(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_V);
				robot.keyRelease(KeyEvent.VK_CONTROL);

				//Wait 1 minute to save PDF. May take longer, further testing is needed.
				TimeUnit.MINUTES.sleep(1);
			}

		} catch (AWTException | InterruptedException e) {
			e.printStackTrace();
		}
	}
}
