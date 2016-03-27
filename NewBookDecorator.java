
public class NewBookDecorator extends Book {
	protected Book decoratedBook;
	
	public NewBookDecorator(Book decoratedBook) {
		this.decoratedBook = decoratedBook;
	}

	// New books cannot be renewed
	@Override
	public boolean renew(Member member) {
		return false;
	}
	@Override
	public boolean checkFines(Member member) {
		long dueDays = decoratedBook.getExtraDays();
		// decoratedBook.checkFines() and then add the diff - more Decorator like
		
		if (dueDays == 1) {
			member.addAmountToFine(25 * decoratedBook.getRaiseAmount());
			return true;
		}
		if (dueDays > 1){
			member.addAmountToFine(10 * decoratedBook.getRaiseAmount());
			return true;
		}
		return false;
	}

	@Override
	public boolean issue(Member member) {
		return decoratedBook.issue(member);
	}
}
