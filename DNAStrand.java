
public interface DNAStrand {
	String getName();
	void setName(String name);
	char getBaseAt(int idx);
	int getLength();
	DNAStrand extract(int start, int end);
	DNAStrand join(DNAStrand tail);
	int findSubstrand(DNAStrand substrand);
	int findSubstrand(DNAStrand substrand, int search_start_position);
}
