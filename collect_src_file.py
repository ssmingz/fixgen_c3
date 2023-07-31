import json
import os


def is_constructor(method):
    result = None
    method_sig = method.split('(')[0].strip().split(' ')
    group_size = len(method_sig)
    if 'private' in method_sig or 'public' in method_sig or 'protected' in method_sig:
        group_size -= 1
    if group_size < 2:
        result = method_sig[-1]
    return result


# ant,junit,checkstyle,cobertura,swt,drjava
pro_name = "ant"
load_json = f"./resources/{pro_name}_difference.json"
dataset = json.load(open(load_json, 'r'))

result_txt = f"./resources/{pro_name}.txt"
output_base = f"./dataset"
good_list = []
size_map = dict()
with open(result_txt, "r") as f:
    for l in f:
        idx = int(l.split(":")[0].strip())
        result = l.split(":")[-1].strip()
        if result == "g":
            target_list = dataset[idx]
            size_map[idx] = len(target_list)
            for i in range(0, len(target_list)):
                pair_dir = f"{output_base}/{pro_name}/{idx}/{i}"
                if not os.path.exists(pair_dir):
                    os.makedirs(pair_dir)
                srcpath = f"{pair_dir}/before.java"
                tarpath = f"{pair_dir}/after.java"

                target = target_list[i]

                clazz_name = is_constructor(target['methodBefore'])
                if clazz_name is None:
                    clazz_name = 'PlaceHold'
                # else:
                #     print(f"{pro_name} {idx} {i}")
                with open(srcpath, "w", encoding="utf8") as fb:
                    fb.write(f"class {clazz_name}" + "{\n" + target['methodBefore'] + "\n}")
                    fb.close()
                os.system(f"java -jar resources/google-java-format-1.17.0-all-deps.jar -r {srcpath}")
                print(f"finish {srcpath}")
                with open(tarpath, "w", encoding="utf8") as fa:
                    fa.write(f"class {clazz_name}" + "{\n" + target['methodAfter'] + "\n}")
                    fa.close()
                os.system(f"java -jar resources/google-java-format-1.17.0-all-deps.jar -r {tarpath}")
                print(f"finish {tarpath}")
with open(f"{output_base}/{pro_name}/{pro_name}.txt", "w") as f:
    f.write("index,size\n")
    for idx,size in size_map.items():
        f.write(f"{idx},{size}\n")
