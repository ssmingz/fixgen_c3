MORE_THAN_ONE="[warn]More than one pattern:"
LESS_THEN_SIMILARITY_THRES="[warn]Not satisfy SIMILARITY_THRESHOLD:"
ALL_ABS="[error]all abstracted actions:"
INVALID_ACTION_ABS="[error]action node has invalid-abstracted source:"
INDEX_NOT_FIND="[error]Pattern and json result not match:"

filepath="/Users/yumeng/JavaProjects/FixGen/model_log/progress.log"
error1, error2, error3, error4, error5=set(), set(), set(), set(), set()
with open(filepath, "r") as f:
    for l in f:
        l = l.strip()
        if MORE_THAN_ONE in l:
            group = l.split(" ")[-2]
            index = l.split(" ")[-1]
            error1.add(f"{group}-{index}")
        elif LESS_THEN_SIMILARITY_THRES in l:
            group = l.split("/")[-3]
            index = l.split("/")[-2]
            error2.add(f"{group}-{index}")
        elif ALL_ABS in l:
            group = l.split(" ")[-2]
            index = l.split(" ")[-1]
            error3.add(f"{group}-{index}")
        elif INVALID_ACTION_ABS in l:
            group = l.split(" ")[-2]
            index = l.split(" ")[-1]
            error4.add(f"{group}-{index}")
        elif INDEX_NOT_FIND in l:
            group = l.split(" ")[-2]
            index = l.split(" ")[-1]
            error5.add(f"{group}-{index}")
print(f"{MORE_THAN_ONE}{len(error1)}")
print(f"{LESS_THEN_SIMILARITY_THRES}{len(error2)}")
print(f"{ALL_ABS}{len(error3)}")
print(f"{INVALID_ACTION_ABS}{len(error4)}")
print(f"{INDEX_NOT_FIND}{len(error5)}")
print(f"total:{len(error1)+len(error2)+len(error3)+len(error4)+len(error5)}")

