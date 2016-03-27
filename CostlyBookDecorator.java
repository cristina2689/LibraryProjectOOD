
public class CostlyBookDecorator extends Book {

	protected Book decoratedBook;

	public CostlyBookDecorator(Book decoratedBook) {
		this.decoratedBook = decoratedBook;
	}

	//A costly book cannot be issued to a member who has unpaid fines.
	@Override
	public boolean issue(Member member) {
		if (member.getFine() > 0)
		return false;
		return decoratedBook.issue(member);
	}

	//A costly book cannot be renewed (<=> from issue) if unpaid fines.
	@Override
	public boolean renew(Member member) {
		if (member.getFine() > 0)
			return false;
		return decoratedBook.issue(member);
	}
	@Override
	public boolean checkFines(Member member) {
		return decoratedBook.checkFines(member);
	}
}
