
public class CostlyBookDecorator implements NormalBook {

	protected NormalBook decoratedBook;

	public CostlyBookDecorator(NormalBook decoratedBook) {
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
		// TODO Cannot berenew to members that have unpaid fines
		return false;
	}
}
