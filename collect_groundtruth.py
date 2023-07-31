import os
import Diff

# ant,junit,checkstyle,cobertura
pro_name = "junit"
base = "/Users/yumeng/PycharmProjects/c3/dataset/"
ground_truth = dict()
for group in os.listdir(f"{base}/{pro_name}"):
    if os.path.isdir(f"{base}/{pro_name}/{group}"):
        for pair in os.listdir(f"{base}/{pro_name}/{group}"):
            diff_path = f"{base}/{pro_name}/{group}/{pair}/diff.diff"
            if os.path.exists(diff_path):
                diff_lines = Diff.get_diff_line(diff_path)
                for k,v in diff_lines.items():
                    ground_truth[k] = v

# result = {}
# finished = set()
# with open("predict.log", "r") as f:
#     for l in f:
#         if l.startswith("[finished]"):
#             finished.add(int(l.strip().split("/")[7]))
#         if l.startswith("[buggy line]"):
#             l = l.strip().replace("[buggy line]", "")
#             fname = l.split("#")[0]
#             line = int(l.split("#")[1])
#             if fname not in result.keys():
#                 result[fname] = {line}
#             else:
#                 result[fname].add(line)
#
# find_all = 0
# all_right = 0
# for fname,lines in result.items():
#     gt = ground_truth[fname]
#     all_in = True
#     for l in gt:
#         if l not in lines:
#             all_in = False
#     if all_in:
#         find_all += 1
#     all_correct = True
#     for l in lines:
#         if l not in gt:
#             all_correct = False
#     if all_correct:
#         all_right += 1
# print(f"{1.0*find_all / len(result)}")
# print(f"{1.0*all_right / len(result)}")
# print(sorted(finished))
