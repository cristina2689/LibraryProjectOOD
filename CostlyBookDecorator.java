
public class CostlyBookDecorator extends Book {

	protected Book decoratedBook;

	public CostlyBookDecorator(Book decoratedBook) {
		this.decoratedBook = decoratedBook;
	}
	@Override
	public boolean issue(Member member) {
		// TODO Cannot be issued to members that have unpaid fines
		return false;
	}

	@Override
	public Member returnBook() {
		// TODO Check fines
		return null;
	}

	@Override
	public boolean renew(Member member) {
		// TODO Cannot be renewed to members that have unpaid fines
		return false;
	}
	@Override
	public boolean checkFines(Member member) {
		// TODO Auto-generated method stub
		return false;
	}
}
