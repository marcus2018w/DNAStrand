
public class ExtractedDNAStrand implements DNAStrand{
	private DNAStrand source_strand;
	private String name;
	private int start;
	private int end;
	
	public ExtractedDNAStrand(DNAStrand source_strand, int start, int end, String name) {
		if( source_strand == null){
			throw new RuntimeException("Illegal, you need a source strand");
		}
		if( start > end || end <0){
			throw new RuntimeException("Illegal start or end number");
		}
		setName(name);
		this.source_strand = source_strand;
		this.start = start;
		this.end = end;
		
	}

	public ExtractedDNAStrand(DNAStrand source_strand, int start, int end) {
		this( source_strand, start, end, null);
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		if (name==(null)) {
			this.name = "Unnamed";
		} else {
			this.name = name;
		}
	}

	public char getBaseAt(int idx) {
		if (idx < 0||idx > getLength()) {
			throw new RuntimeException("Illegal, base, get base at, extracteddnastrand");
		}

			return this.source_strand.getBaseAt(idx + this.start);
		
	}

	public int getLength() {
		return (this.end - this.start + 1);
		
	}

	public DNAStrand extract(int start, int end) { 
		if ((start < 0 )||(start > end)||(end>= getLength())) {
			throw new RuntimeException("Illegal start, end, extract, extracted dna");
		}
		
		 return new ExtractedDNAStrand(this, start, end);
		
	}

	public DNAStrand join(DNAStrand tail) {
		if ( tail == null){
			throw new RuntimeException("Illegal tail, join, extracted dna");
		}
		return new JoinedDNAStrand(this,tail);
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

