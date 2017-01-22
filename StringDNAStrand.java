
public class StringDNAStrand implements DNAStrand {
	private String bases;
	private String name;

	public StringDNAStrand(String base_string, String name) {
		if (base_string.matches("[ATGC]+")) {
			this.bases = base_string;
		} else {
			throw new RuntimeException("Illegal");
		}
		setName(name);
	}

	public StringDNAStrand(String strand_string) {
		this(strand_string, null);
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		if (name == null) {
			this.name = "Unnamed";
		} else {
			this.name = name;
		}
	}

	public char getBaseAt(int idx) {
		if (idx < 0 || idx >=getLength()) {
			throw new RuntimeException("Illegal");
		}

		return this.bases.charAt(idx);
	}

	public int getLength() {
		return this.bases.length();
	}

	public DNAStrand extract(int start, int end) {
		if ((start < 0) ||(start > end)||(end >getLength())) {
			throw new RuntimeException("Illegal base index");
		}

	return new ExtractedDNAStrand(this, start, end);
		
	}

	public DNAStrand join(DNAStrand tail) {
		if (tail == null) {
			throw new RuntimeException("Illegal");
		}
		return new JoinedDNAStrand(this, tail);
		
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
