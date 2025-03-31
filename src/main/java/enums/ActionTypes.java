package enums;

public class ActionTypes {
	public enum ActionType {
		ENTER_TEXT("Entering text into"), CLICK("Clicking");

		private final String logPrefix;

		ActionType(String logPrefix) {
			this.logPrefix = logPrefix;
		}

		public String getLogPrefix() {
			return logPrefix;
		}
	}
}
