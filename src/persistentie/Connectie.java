package persistentie;

public class Connectie {

	/*
	 * INFO: Hieronder dien je uiteraard het schema, de username en het paswoord nog
	 * aan te passen. => vichogent.be:40094/G74 => G74 =>
	 * RyYNGVMqAI0m06amurTA0MQwfm3yG8XZ
	 */

	// public static final String JDBC_URL =
	// "jdbc:mysql://vichogent.be:40094/G100?user=G100&password=rNwn1z4CpVBSBYRzkPZwz7SkBcf6efrW";
	// --> orginele ik heb het aangepast naar ons ding

	public static String JDBC_URL = "jdbc:mysql://vichogent.be:40094/G74?user=G74&password=RyYNGVMqAI0m06amurTA0MQwfm3yG8XZ";

	// als offlinemodus aanstaat gaat de jdbc module het lokale databank bestand in
	// ./data/gamedata gebruiken
	public static void veranderUrlNaarOffline() {
		JDBC_URL = "jdbc:h2:file:./data/gamedata;MODE=MySQL";
	}

}
