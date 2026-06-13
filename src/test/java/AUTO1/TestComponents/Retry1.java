package AUTO1.TestComponents;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class Retry1 implements IRetryAnalyzer {
	int count = 0;
	int maxtry = 1;
	@Override
	public boolean retry(ITestResult result) {

		if (count < maxtry) {
			count++;
			System.out.println("count"+count);
			return true;
		}
		return false;
	}

}
