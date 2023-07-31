EXCEPTION = "[error]Unknown exception"
CREATE_ERROR = "[error]Create new spoon node failed:"
TIMEOUT = "[error]Timeout"

log_path = "genPatch.log"
ids, create_errors, unknown_exceptions, timeout = dict(), dict(), dict(), dict()
with open(log_path, "r") as f:
    pro, group, targetNo = None, None, None
    for l in f:
        l = l.strip()
        if l.startswith("[start]"):
            #[start]D:\expdata\c3\dataset\junit\13\0\before.java
            pro = l.split("\\")[-4]
            group = l.split("\\")[-3]
            targetNo = l.split("\\")[-2]
            if pro not in create_errors.keys():
                create_errors[pro] = set()
            if pro not in unknown_exceptions.keys():
                unknown_exceptions[pro] = set()
            if pro not in timeout.keys():
                timeout[pro] = set()
            if pro not in ids.keys():
                ids[pro] = set()
            ids[pro].add(f"{group}-{targetNo}")
        elif l == EXCEPTION:
            unknown_exceptions[pro].add(f"{group}-{targetNo}")
        elif l.startswith(CREATE_ERROR):
            create_errors[pro].add(f"{group}-{targetNo}")
        elif l == TIMEOUT:
            timeout[pro].add(f"{group}-{targetNo}")
# total
excep_num = sum([len(i) for i in unknown_exceptions.values()])
errors_num = sum([len(i) for i in create_errors.values()])
timeout_num = sum([len(i) for i in timeout.values()])
total_instance = sum([len(i) for i in ids.values()])
print(f"Unknown exception: {excep_num}/{total_instance}")
print(f"Create error: {errors_num}/{total_instance}")
print(f"Timeout error: {timeout_num}/{total_instance}")
# each project
for pro, li in ids.items():
    print(f"===== {pro} =====")
    print(f"Unknown exception: {len(unknown_exceptions[pro])}/{len(li)}")
    print(f"Create error: {len(create_errors[pro])}/{len(li)}")
    print(f"Timeout error: {len(timeout[pro])}/{len(li)}")