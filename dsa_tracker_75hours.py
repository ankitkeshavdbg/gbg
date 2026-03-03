#code written with gemini: https://gemini.google.com/app/c5853b3130ca6f0c
import pandas as pd

def build_dsa_tracker(filename='DSA_Revision_Master.xlsx'):
    # --- 1. THE DATASET ---
    raw_data = {
        "Function and array": ["Introduction To Functions", "Digit Frequency", "Introduction To Number System", "Decimal To Any Base", "Any Base To Decimal", "Any Base To Any Base", "Any Base Addition", "Any Base Subtraction", "Any Base Multiplication", "Introduction to Arrays", "Arrays - Memory Management", "Span Of Array", "Find Element In An Array", "Bar Chart", "Sum Of Two Arrays", "Difference Of Two Arrays", "Reverse An Array", "Rotate An Array", "Inverse Of An Array", "Subarray Problem", "Binary Search", "Broken Economy", "First Index And Last Index", "Subsets Of Array", "Inverted Bar Chart"],
        "Time and space": ["Time and space complexity", "Bubble sort", "Selection sort", "Insertion sort", "Merge two sorted arrays", "Merge sort", "Partition An Array", "Quick sort", "Quick Select", "Count sort", "Radix Sort", "Sort Dates", "Sort 01", "Sort 012", "Target Sum pair 1", "Pivot of sorted and rotated array", "Linear Search vs Binary Search (Theory)", "Exp. Comparison (Bubble vs Merge)"],
        "2d array": ["2d Arrays Demo", "Matrix Multiplication", "Wave traversal", "Spiral Display", "Exit Point Of A Matrix", "Rotate By 90 Degree", "Shell rotate", "Diagonal traversal", "Saddle point", "Search in sorted 2d array"],
        "Strings & ArrayLists": ["Intro to Strings/Stringbuilders/ArrayLists", "Print All Palindromic Substrings", "String Compression", "String - Interning & Immutability", "Stringbuilder - Usage And Performance", "Toggle Case", "String with Diff of Consecutive Chars", "Print All Permutations Iteratively", "Introduction to Arraylists", "Remove Primes"],
        "Stacks and Queues": ["Introduction to Stacks", "Duplicate Brackets", "Balanced Brackets", "Next Greater Element Right", "Stock Span", "Largest Area Histogram", "Sliding Window Maximum", "Infix Evaluation", "Infix Conversions", "Celebrity Problem", "Postfix Eval & Conversions", "Merge Overlapping Intervals", "Smallest number following Pattern", "Build Normal Stack", "Build Dynamic Stack", "Queues - Intro", "Prefix Eval & Conversion", "Build Normal Queue", "Build Dynamic Queue", "Minimum Stack - 1", "Minimum Stack - 2", "Q to Stack Adapter", "Stack to Q Adapter", "Two Stacks in an Array"],
        "Recursion": ["Print Decreasing/Increasing", "Factorial", "Power-linear", "Power Logarithmic", "Print Zigzag", "Tower Of Hanoi", "Display Arrays", "Max of an Array", "First/Last/All Indices", "Get Subsequence", "Get Keypad combination", "Get Stair Paths", "Get Maze Paths", "Print Subsequence", "Print Keypad", "Print Stairs", "Print Maze", "Print Permutation", "Print Encodings", "Flood Fill", "Target Sum Subsets", "N Queens", "Knights Tour"],
        "Linked Lists": ["Intro & Data Members", "Add Last/First/Index", "Display and Size", "Remove First/Last/Index", "Get Value", "Reverse - Data Iterative", "Reverse - Pointer Iterative", "LL to Stack Adapter", "LL to Queue Adapter", "Kth element from end", "Middle of LL", "Merge two sorted LL", "Merge Sort LL", "Remove duplicates", "Odd Even List", "K Reverse in LL", "Display Reverse LL Recursive", "Reverse LL Pointer Recursive", "Reverse LL Data Recursive", "Is LL Palindrome", "Fold a LL", "Add two LLs", "Intersection point of LL", "LL Intersection (Floyd)", "Cycle Node In LL"],
        "Generic Tree": ["Intro & Constructor", "Display", "Size, Max, Height", "Traversals", "Level Order", "Mirror Generic Tree", "Remove Leaves", "Linearize Tree", "Find element", "LCA", "Distance between Nodes", "Similar/Mirror Shapes", "Is Tree Symmetric", "Multisolver", "Predecessor and Successor", "Ceil and Floor", "Kth Largest Element", "Node with Max Subtree Sum", "Diameter of Generic Tree", "Iterative Pre/Post-order"],
        "Binary Tree": ["Intro & Display", "Size, Sum, Max, Height", "Traversals", "Node to Root Path", "Print K Levels Down", "Print Nodes K Level Far", "Path to Leaf from Root", "Transform to Left Cloned", "Print Single Child Nodes", "Remove Leaves", "Diameter / Tilt", "Is tree BST / Balanced", "Largest BST Subtree", "Construct from Post/Pre & InOrder", "Bottom View"],
        "BST": ["Introduction to BST", "Constructor", "Size, Sum, Max & Min", "Add/Remove Node", "Replace Sum of Larger", "LCA in BST", "Target Sum Pair", "Is Cycle Present"],
        "Hash map & heaps": ["Hashmap Intro", "Highest Frequency Character", "Get Common Elements", "Longest Consecutive Sequence", "Heaps Intro", "K Largest Elements", "Sort K-sorted Array", "Median Priority Queue", "Merge K Sorted Lists", "Write Priority Queue", "Write Hashmap", "Heap - Comparable vs Comparator"],
        "Dynamic Programming": ["Intro & Memoization", "Climbing Stairs", "Minimum Cost Path", "Goldmine", "Target Sum Subsets", "Coin Change", "0-1 Knapsack", "Unbounded Knapsack", "Count Binary Strings", "Arrange Buildings", "Decode Ways", "Count Subsequences", "Max Sum Non Adjacent", "Paint House", "Paint Fence", "Tiling", "Friends Pairing", "Stocks (All Variants)", "Highway Billboard"],
        "Graph": ["Intro & Representation", "Find Path / All Paths", "Connected Comp", "Is Connected", "Count Islands", "Perfect Friends", "Hamiltonian Path", "Knights Tour", "BFS", "Is Graph Cyclic", "Is Graph Bipartite", "Spread Infection", "Dijkstra Algorithm", "Prim's Algorithm", "Topological Sort", "Iterative DFS"]
    }

    rows = []
    for main, subs in raw_data.items():
        for sub in subs:
            rows.append([main, sub])

    df = pd.DataFrame(rows, columns=['Main Topic', 'Subtopic'])
    df['Status'] = "Pending"
    df['Rev Count'] = 0
    df['Input (1-5)'] = 1
    df['Confidence'] = ""
    df['Last Revised'] = ""
    df['Next Revision'] = ""

    # --- 2. SETUP EXCEL WRITER ---
    writer = pd.ExcelWriter(filename, engine='xlsxwriter')
    start_row = 10
    df.to_excel(writer, sheet_name='Tracker', index=False, startrow=start_row)
    
    workbook  = writer.book
    worksheet = writer.sheets['Tracker']

    # --- 3. ELEGANT FORMATS ---
    # Header: Light Grey-Blue with Dark Text
    header_fmt = workbook.add_format({
        'bold': True, 
        'bg_color': '#F2F2F2', 
        'font_color': '#333333', 
        'border': 1,
        'align': 'center',
        'valign': 'vcenter'
    })
    
    date_fmt = workbook.add_format({'num_format': 'yyyy-mm-dd', 'border': 1, 'align': 'center'})
    
    # Title: Clean and minimalist
    title_fmt = workbook.add_format({
        'bold': True, 
        'font_size': 18, 
        'font_color': '#444444', 
        'align': 'center', 
        'valign': 'vcenter'
    })
    
    # Functional Colors (Confidence/Status)
    conf_formats = {
        'Clueless':    workbook.add_format({'bg_color': '#FFC7CE', 'font_color': '#9C0006'}),
        'Logic Clear': workbook.add_format({'bg_color': '#FFEB9C', 'font_color': '#9C6500'}),
        'Hand held':    workbook.add_format({'bg_color': '#DEEBF7', 'font_color': '#217346'}),
        'Independent': workbook.add_format({'bg_color': '#C6EFCE', 'font_color': '#006100'}),
        'Expert':      workbook.add_format({'bg_color': '#228B22', 'font_color': '#FFFFFF', 'bold': True})
    }
    revise_alert = workbook.add_format({'bg_color': '#FF0000', 'font_color': '#FFFFFF', 'bold': True})

    # --- 4. REVISION INTERVALS ---
    logic_sheet = workbook.add_worksheet('Revision_Intervals')
    intervals = [[0,0],[1,1],[2,3],[3,7],[4,15],[5,30],[6,90],[7,180]]
    for r, row_data in enumerate(intervals): logic_sheet.write_row(r, 0, row_data)

    # --- 5. EXCEL TABLE (Styling) ---
    num_rows = len(df)
    table_range = f'A11:H{11 + num_rows}'
    
    # Using 'Table Style Light 1' for a clean, non-banded look
    worksheet.add_table(table_range, {
        'name': 'DSATracker',
        'columns': [{'header': col, 'header_format': header_fmt} for col in df.columns],
        'style': 'Table Style Light 1',
        'banded_rows': False,
        'autofilter': True
    })

    # --- 6. APPLY DYNAMIC FORMULAS ---
    for i in range(num_rows):
        r = start_row + 2 + i
        worksheet.write_formula(f'C{r}', f'=IF(AND(H{r}<>"", H{r}<=TODAY()), "⚠️ REVISE", "✅ OK")')
        worksheet.write_formula(f'F{r}', f'=CHOOSE(E{r}, "Clueless", "Logic Clear", "Hand held", "Independent", "Expert")')
        worksheet.write_formula(f'H{r}', f'=IF(OR(D{r}=0, G{r}=""), "", G{r} + IFERROR(VLOOKUP(D{r}, Revision_Intervals!$A$1:$B$8, 2, FALSE), 0))', date_fmt)

    # --- 7. CONDITIONAL FORMATTING ---
    worksheet.conditional_format('C12:C1000', {'type': 'cell', 'criteria': 'equal to', 'value': '"⚠️ REVISE"', 'format': revise_alert})
    for label, fmt in conf_formats.items():
        worksheet.conditional_format('F12:F1000', {'type': 'cell', 'criteria': 'equal to', 'value': f'"{label}"', 'format': fmt})

    # --- 8. UI/UX ---
    worksheet.merge_range('A1:H3', 'DSA REVISION MASTER TRACKER', title_fmt)
    worksheet.write('A5', 'Note: Colors appear only in Status and Confidence columns based on your progress.', workbook.add_format({'font_color': '#777777', 'italic': True}))
    
    worksheet.set_column('A:B', 28) 
    worksheet.set_column('C:H', 15) 
    
    writer.close()
    print(f"Generated {filename} (Elegant Version) with {len(df)} topics.")

if __name__ == "__main__":
    build_dsa_tracker()