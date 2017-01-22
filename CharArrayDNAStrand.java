
public class CharArrayDNAStrand implements DNAStrand {
	private char[] bases;
	private String name;

	public CharArrayDNAStrand(char[] base_array, String name) {

		for (int i = 0; i < base_array.length; i++) {

			if (!(base_array[i] == 'A') && !(base_array[i] == 'G')
					&& !(base_array[i] == 'T') && !(base_array[i] == 'C')) {
				throw new RuntimeException("Illegal, Bases aren't A G T or C");
			}
		}

		this.bases = base_array;
		setName(name);
	}

	public CharArrayDNAStrand(char[] base_array) {
		this(base_array, null);
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		if (name == null) {
			// XXX Don't forget the "this"!
			this.name = "Unnamed";
		} else {
			this.name = name;
		}

	}

	public char getBaseAt(int idx) {
		if (idx < 0 || idx >= this.bases.length) {
			throw new RuntimeException("Illegal index");
		}
		return this.bases[idx];
	}

	public int getLength() {
		return this.bases.length;
	}

	public DNAStrand extract(int start, int end) {
		if (start < 0 ||start > end || end> bases.length) {
			throw new RuntimeException("Illegal start or end");
		}


		return new ExtractedDNAStrand(this, start, end);


		// XXX This is creating a strand with the name ""
		// XXX return new CharArrayDNAStrand(baseArray, "");

		// XXX We want a strand with the default name "Unnamed"
	}

	public DNAStrand join(DNAStrand tail) {
		if (tail == null) {
			throw new RuntimeException("Illegal, no tail length");
		}

		JoinedDNAStrand join = new JoinedDNAStrand(this, tail);
		return join;

	}
	public int findSubstrand(DNAStrand substrand) {
		return findSubstrand(substrand, 0);
	}

	public int findSubstrand(DNAStrand substrand, int search_start_position) {
		int j;
		int dna = -1;
		boolean test = false;
		for (int i = search_start_position; i < this.getLength(); i++) {
			if (this.getBaseAt(i) == substrand.getBaseAt(0)) {
				dna = i;
				for (j = 0; j < substrand.getLength(); j++) {
					if (this.getBaseAt(i + j) == substrand.getBaseAt(j)) {

						test = true;
					} else {
						test = false;
						break;
					}

				}
				if (test == true) {
					break;
				}
			}
		}
		if (test == true) {
			return dna;
		} else {
			return -1;
		}

	}
}
