package util;

public enum User {
	Test("testjohndoe92@gmail.com", "test"),
	NegativeTest("pdW6tUs7O75S20dOfn6tDJ5eILwk@mail.com","asdfjdslfsdj"),
	NegativeTest2("testjohndoe92@gmail.com",""),
	NegativeTest3("","asdfjdslfsasdfjdslfsdjdj");
	public String EMAIL;
	public String PSWD;
	
	User(String EMAIL, String PSWD) {
		this.EMAIL = EMAIL;
		this.PSWD = PSWD;
	}
}
