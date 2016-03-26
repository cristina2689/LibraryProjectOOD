
public class NewBookDecorator implements NormalBook {
	protected NormalBook decoratedBook;
	
	public NewBookDecorator(NormalBook decoratedBook) {
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

}
