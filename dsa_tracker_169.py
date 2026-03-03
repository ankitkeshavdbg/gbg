import pandas as pd

def build_dsa_tracker(filename='DSA_Revision_8_Weeks.xlsx'):
    # --- 1. THE DATASET (Updated to your 8-Week structure) ---
    raw_data = {
        "Week 1": [
            "Two Sum", "Valid Parentheses", "Merge Two Sorted Lists", "Best Time to Buy and Sell Stock",
            "Valid Palindrome", "Invert Binary Tree", "Valid Anagram", "Binary Search", "Flood Fill",
            "Lowest Common Ancestor of a Binary Search Tree", "Balanced Binary Tree", "Linked List Cycle",
            "Implement Queue using Stacks", "First Bad Version", "Ransom Note", "Climbing Stairs",
            "Longest Palindrome", "Reverse Linked List", "Majority Element", "Add Binary",
            "Diameter of Binary Tree", "Middle of the Linked List", "Maximum Depth of Binary Tree",
            "Contains Duplicate", "Meeting Rooms", "Roman to Integer", "Backspace String Compare",
            "Counting Bits", "Same Tree", "Number of 1 Bits", "Longest Common Prefix", "Single Number"
        ],
        "Week 2": [
            "Palindrome Linked List", "Move Zeroes", "Symmetric Tree", "Missing Number", "Palindrome Number",
            "Convert Sorted Array to Binary Search Tree", "Reverse Bits", "Subtree of Another Tree",
            "Squares of a Sorted Array", "Maximum Subarray", "Insert Interval", "01 Matrix",
            "K Closest Points to Origin", "Longest Substring Without Repeating Characters", "3Sum",
            "Binary Tree Level Order Traversal", "Clone Graph", "Evaluate Reverse Polish Notation",
            "Course Schedule", "Implement Trie (Prefix Tree)", "Coin Change", "Product of Array Except Self",
            "Min Stack", "Validate Binary Search Tree"
        ],
        "Week 3": [
            "Number of Islands", "Rotting Oranges", "Search in Rotated Sorted Array", "Combination Sum",
            "Permutations", "Merge Intervals", "Lowest Common Ancestor of a Binary Tree", 
            "Time Based Key-Value Store", "Accounts Merge", "Sort Colors", "Word Break",
            "Partition Equal Subset Sum", "String to Integer (atoi)", "Spiral Matrix", "Subsets",
            "Binary Tree Right Side View", "Longest Palindromic Substring", "Unique Paths",
            "Construct BT from Preorder and Inorder Traversal", "Container With Most Water"
        ],
        "Week 4": [
            "Letter Combinations of a Phone Number", "Word Search", "Find All Anagrams in a String",
            "Minimum Height Trees", "Task Scheduler", "LRU Cache", "Kth Smallest Element in a BST",
            "Daily Temperatures", "House Robber", "Gas Station", "Next Permutation", "Valid Sudoku",
            "Group Anagrams", "Maximum Product Subarray", "Design Add and Search Words Data Structure",
            "Pacific Atlantic Water Flow", "Remove Nth Node From End of List", "Shortest Path to Get Food",
            "Find the Duplicate Number", "Top K Frequent Words"
        ],
        "Week 5": [
            "Longest Increasing Subsequence", "Graph Valid Tree", "Course Schedule II", "Swap Nodes in Pairs",
            "Path Sum II", "Longest Consecutive Sequence", "Rotate Array", "Odd Even Linked List",
            "Decode String", "Contiguous Array", "Maximum Width of Binary Tree", "Find K Closest Elements",
            "Longest Repeating Character Replacement", "Inorder Successor in BST", "Jump Game",
            "Add Two Numbers", "Generate Parentheses", "Sort List", "Number of Connected Components in Graph",
            "Minimum Knight Moves"
        ],
        "Week 6": [
            "Subarray Sum Equals K", "Asteroid Collision", "Random Pick with Weight", 
            "Kth Largest Element in an Array", "Maximal Square", "Rotate Image",
            "Binary Tree Zigzag Level Order Traversal", "Design Hit Counter", "Path Sum III",
            "Pow(x, n)", "Search a 2D Matrix", "Largest Number", "Decode Ways", "Meeting Rooms II",
            "Reverse Integer", "Set Matrix Zeroes", "Reorder List", "Encode and Decode Strings",
            "Cheapest Flights Within K Stops", "All Nodes Distance K in Binary Tree"
        ],
        "Week 7": [
            "3Sum Closest", "Rotate List", "Find Minimum in Rotated Sorted Array", "Basic Calculator II",
            "Combination Sum IV", "Insert Delete GetRandom O(1)", "Non-overlapping Intervals",
            "Minimum Window Substring", "Serialize and Deserialize Binary Tree", "Trapping Rain Water",
            "Find Median from Data Stream", "Word Ladder", "Basic Calculator", "Maximum Profit in Job Scheduling",
            "Merge k Sorted Lists", "Largest Rectangle in Histogram", "Binary Tree Maximum Path Sum"
        ],
        "Week 8": [
            "Maximum Frequency Stack", "Median of Two Sorted Arrays", "Longest Increasing Path in a Matrix",
            "Longest Valid Parentheses", "Design In-Memory File System", "Employee Free Time",
            "Word Search II", "Alien Dictionary", "Bus Routes", "Sliding Window Maximum",
            "Palindrome Pairs", "Reverse Nodes in k-Group", "Sudoku Solver", "First Missing Positive",
            "N-Queens", "Smallest Range Covering Elements from K Lists"
        ]
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
    header_fmt = workbook.add_format({
        'bold': True, 'bg_color': '#F2F2F2', 'font_color': '#333333', 
        'border': 1, 'align': 'center', 'valign': 'vcenter'
    })
    date_fmt = workbook.add_format({'num_format': 'yyyy-mm-dd', 'border': 1, 'align': 'center'})
    title_fmt = workbook.add_format({
        'bold': True, 'font_size': 18, 'font_color': '#444444', 
        'align': 'center', 'valign': 'vcenter'
    })
    
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
    intervals = [[0,0],[1,1],[2,3],[3,7],[4,14],[5,30],[6,60],[7,120]]
    for r, row_data in enumerate(intervals): logic_sheet.write_row(r, 0, row_data)

    # --- 5. EXCEL TABLE ---
    num_rows = len(df)
    table_range = f'A11:H{11 + num_rows}'
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
    worksheet.conditional_format('C12:C200', {'type': 'cell', 'criteria': 'equal to', 'value': '"⚠️ REVISE"', 'format': revise_alert})
    for label, fmt in conf_formats.items():
        worksheet.conditional_format('F12:F200', {'type': 'cell', 'criteria': 'equal to', 'value': f'"{label}"', 'format': fmt})

    # --- 8. UI/UX ---
    worksheet.merge_range('A1:H3', '8-WEEK DSA INTENSIVE TRACKER', title_fmt)
    worksheet.set_column('A:B', 35) 
    worksheet.set_column('C:H', 15) 
    
    writer.close()
    print(f"Generated {filename} with {len(df)} problems mapped to 8 weeks.")

if __name__ == "__main__":
    build_dsa_tracker()