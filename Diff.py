"""
    减号表示第一个文件（即f1）,"1"表示第1行,"7"表示连续7行,第一个文件从第1行开始的连续7行。
    "+1,7"表示变动后，成为第二个文件从第1行开始的连续7行。
"""
def get_diff_line(in_path):
    result = {}
    file_name = ""
    del_counter, add_counter = 0, 0
    dels, adds = set(), set()
    start = False
    with open(in_path, "r") as f:
        for l in f:
            if l.startswith("--- "):
                file_name = l.split(" ")[1].strip()
                del_counter, add_counter = 0, 0
                start = False
            if l.startswith("@@ "):
                del_counter = l.strip().split(" ")[1]
                if ',' in del_counter:
                    del_counter = int(del_counter.split(",")[0].split("-")[-1])
                else:
                    del_counter = int(del_counter.split("-")[-1])
                add_counter = l.strip().split(" ")[2]
                if ',' in add_counter:
                    add_counter = int(add_counter.split(",")[0].split("+")[-1])
                else:
                    add_counter = int(add_counter.split("+")[-1])
                if len(dels)!=0 or len(adds)!=0:
                    if file_name not in result.keys():
                        result[file_name] = set()
                    result[file_name] = result[file_name].union(dels.union(adds))
                    dels.clear()
                    adds.clear()
                start = True
            if start and l.startswith("+ "):
                adds.add(add_counter)
                add_counter += 1
            if start and l.startswith("- "):
                dels.add(del_counter)
                del_counter += 1
            if l.startswith(" "):
                del_counter += 1
                add_counter += 1
                print(f"[warn]{file_name}")
    if len(dels) != 0 or len(adds) != 0:
        if file_name not in result.keys():
            result[file_name] = set()
        result[file_name] = result[file_name].union(dels.union(adds))
    for fn,lines in result.items():
        result[fn] = sorted(lines)
    return result
