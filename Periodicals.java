
public class Periodicals extends LoanableItem {

	@Override
	public boolean issue(Member member) {
		/* Periodicals cannot be checked out */
		return false;
	}

	@Override
	public Member returnItem() {
		/* Periodicals cannot be checked out */
		return null;
	}

	@Override
	public boolean renew(Member member) {
		/* Periodicals cannot be checked out */
		return false;
	}


}
