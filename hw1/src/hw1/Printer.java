package hw1;

public class Printer {
	/*
	 * Capacity, in ounces, of a new ink cartridge.
	 */
	public static final double INK_CAPACITY = 2.0;

	/*
	 * Amount of ink, in ounces, used per printed page.
	 */
	public static final double INK_USAGE = 0.0023;
	private int printerCapacity;
	private int numberOfSheets;
	private int paperUsed = 0;
	private double amountOfInk = INK_CAPACITY;

	/*
	 * Constructs a printer that has the given capacity (number of sheets of paper
	 * it can hold). Initially it contains no paper and a full ink cartridge.
	 */
	public Printer(int givenCapacity) {
		printerCapacity = givenCapacity;
		numberOfSheets = 0;
	}

	/*
	 * Constructs a printer that has the given capacity (number of sheets of paper
	 * it can hold). Initially it contains the given number of sheets of paper and a
	 * full ink cartridge. Note that if givenNumberOfSheets is greater than
	 * givenCapacity, then the printer will initially contain givenCapacity sheets
	 * of paper.
	 */
	public Printer(int givenCapacity, int givenNumberOfSheets) {
		printerCapacity = givenCapacity;
		numberOfSheets = givenNumberOfSheets;

	}

	/*
	 * Adds the given number of sheets of paper to this printer, not exceeding the
	 * printer's capacity.
	 */
	public void addPaper(int additionalSheets) {
		numberOfSheets = Math.min(numberOfSheets + additionalSheets, printerCapacity);

	}

	/*
	 * Returns the number of sheets of paper currently in this printer. This value
	 * is never negative.
	 */
	public int getCurrentPaper() {

		return numberOfSheets;
	}

	/*
	 * Returns the total number of sheets of paper printed by this printer since its
	 * construction. Note this is counting sheets of paper, not pages printed, i.e.
	 * sheets used for two sided printing still count as just one sheet.
	 */
	public int getTotalPaperUse() {
		return paperUsed;
	}

	/*
	 * Determines whether the ink has run out. Returns true if the amount of ink
	 * left is smaller than the quantity INK_USAGE needed to print one page.
	 */
	public boolean isInkOut() {
		return (amountOfInk < INK_USAGE);
	}

	/*
	 * Simulates replacement of the ink cartridge, restoring the quantity of ink in
	 * the printer to INK_CAPACITY
	 */
	public void replaceInk() {
		amountOfInk = INK_CAPACITY;
	}

	/*
	 * Simulates printing pages in one-sided mode, using the appropriate number of
	 * sheets and a corresponding quantity of ink. If there is not enough paper, the
	 * printer will use up all remaining paper and will only use the quantity of ink
	 * needed for the sheets actually printed. If there is not enough ink, the
	 * printer will use up all the ink, and will still use up the specified number
	 * of sheets of paper (i.e., it just prints a bunch of blank pages after the ink
	 * runs out).
	 */
	public void print(int numberOfPages) {
		int actualPrinted = Math.min(numberOfSheets, numberOfPages);
		numberOfSheets -= actualPrinted;
		paperUsed += actualPrinted;
		amountOfInk -= (INK_USAGE * actualPrinted);

	}

	/*
	 * Simulates printing pages in two-sided mode, using the appropriate number of
	 * sheets and a corresponding quantity of ink. If there is not enough paper, the
	 * printer will use up all remaining paper and will only use the quantity of ink
	 * needed for the sheets actually printed. If there is not enough ink, the
	 * printer will use up all the ink, and will still use up the specified number
	 * of sheets of paper (i.e., it just prints a bunch of blank pages after the ink
	 * runs out).
	 */
	public void printTwoSided(int numberOfPages) {
		int twoSidedPages = numberOfPages / 2 + numberOfPages % 2;
		int actualPrinted = Math.min(numberOfSheets, twoSidedPages);
		numberOfSheets -= actualPrinted;
		paperUsed += actualPrinted;
		amountOfInk -= (INK_USAGE * 2) * actualPrinted;
	}
}
