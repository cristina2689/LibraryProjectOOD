
public class ItemFactory {
	public static LoanableItem buildItem(String type, String title, String author,
									String id, Integer duration) {
	LoanableItem item = null;	
		switch (type){
		case "book":
			item = new Book(title, author, id);
			break;
		case "CD":
			item = new CD(title, id, duration);
			break;
		case "DVD":
			item = new DVD(title, id, duration);
			break;
		case "laptop":
			break;
		default:
			throw new IllegalArgumentException("Invalid item "+ type);
		}
		return item;
	}
}


