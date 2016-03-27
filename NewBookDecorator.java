
public class NewBookDecorator extends Book {
	protected Book decoratedBook;
	
	public NewBookDecorator(Book decoratedBook) {
		this.decoratedBook = decoratedBook;
	}
	@Override
	public boolean issue(Member member) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Member returnBook() {
		// TODO Fines are bigger for new books
		return null;
	}

	@Override
	public boolean renew(Member member) {
		// TODO Cannot be renewed
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
}
