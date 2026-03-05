// Laughing Gas, scientifically known as Nitrous Oxide (N₂O), is a chemical compound 
// made of 2 Nitrogen atoms (N) and 1 Oxygen atom (O)

// You are simulating a chemical synthesis system where multiple threads represent 
// atoms arriving independently into a reactor.

// Each atom is processed by its own thread:
//     - A Nitrogen thread represents one Nitrogen atom.
//     - An Oxygen thread represents one Oxygen atom.

// ⚙️ Synchronization Rule
// ------------------------
// A Laughing Gas molecule (N₂O) can be formed only when:
//     - Exactly 2 Nitrogen threads
//     - Exactly 1 Oxygen thread
// are allowed to proceed together.

// ⚠️ No atom is allowed to be released unless it participates in forming a complete Laughing Gas molecule.

// 🎯 Your Goal:
// -------------
// Design a thread-safe Java program that ensures:
//     - Nitrogen threads print "N"
//     - Oxygen threads print "O"
// Atoms are printed only in valid N₂O groupings

// Each molecule must appear on a new line, for example:
// N N O

// The order of N and O within a molecule is not fixed, but each line must contain 
// exactly two Ns and one O.

// Input Format
// ------------
// A string S containing only characters 'N' and 'O'
//     Length of S is guaranteed to be a valid multiple of 3

// Output Format
// -------------
// Print atoms only when a complete Laughing Gas molecule is formed.
// Each molecule must be printed on a separate line.


// Sample Input-1
// --------------
// NNONNO

// Sample Output-1
// ---------------
// N N O
// N N O

// Sample Input-2:
// ---------------
// ONNNNO

// Sample Output:
// --------------
// O N N
// N N O

// Sample Input-3
// --------------
// ONNNNNNOO

// Sample Output-3
// ---------------
// O N N
// N N O
// N N O

