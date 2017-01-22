
public class JoinedDNAStrand implements DNAStrand{

	private DNAStrand head;
	private DNAStrand tail;
	private String name;
	
	public JoinedDNAStrand(DNAStrand head, DNAStrand tail, String name) {
		if(head ==null|| tail==null){
			throw new RuntimeException("Illegal, you need a value for the strand");
		}
		this.head = head;
		this.tail = tail;
	setName(name);
	}

	public JoinedDNAStrand(DNAStrand head, DNAStrand tail) {
		this( head, tail, null);
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
		if (idx < 0|| idx>= getLength()) {
			throw new RuntimeException("Illegal base index, joined dna strand");
		}
		if( idx < this.head.getLength()){
			return this.head.getBaseAt(idx);
		}
		else{
			return tail.getBaseAt(idx - this.head.getLength());
		}
	}

	public int getLength() {
		return tail.getLength() + head.getLength();
	}

	public DNAStrand extract(int start, int end) {
		if ((start < 0)||(start > end)||( end >= this.getLength())) {
			throw new RuntimeException("Illegal start, or end");
		}

	return new ExtractedDNAStrand(this, start, end);
	}

	public DNAStrand join(DNAStrand tail) {
		if ( tail == null){
			throw new RuntimeException("Illegal tail is null");
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
