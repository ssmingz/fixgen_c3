ALL_INSTANCES = "[time]build all instance action graphs:"
TARGET_CG = "[time]build target codegraph:"
CLUSTER = "[time]cluster instance graphs:"
ABSTRACT = "[time]abstract pattern:"
APPLY = "[time]apply pattern:"

log_path = "log.txt"
ids, all_instances, target_cgs, clusters, abss, applys = dict(), dict(), dict(), dict(), dict(), dict()
with open(log_path, "r") as f:
    pro, group, targetNo = None, None, None
    for l in f:
        l = l.strip()
        if l.startswith("[prepare]"):
            # [prepare]build action graph: ant 0 0
            pro = l.split(" ")[-3]
            group = l.split(" ")[-2]
            targetNo = l.split(" ")[-1]
            if pro not in all_instances.keys():
                all_instances[pro] = set()
            if pro not in target_cgs.keys():
                target_cgs[pro] = set()
            if pro not in clusters.keys():
                clusters[pro] = set()
            if pro not in abss.keys():
                abss[pro] = set()
            if pro not in applys.keys():
                applys[pro] = set()
            if pro not in ids.keys():
                ids[pro] = set()
            ids[pro].add(f"{group}-{targetNo}")
        elif l.startswith(ALL_INSTANCES):
            # [time]build all instance action graphs: 7.331000 s
            time = float(l.split(" ")[-2])
            all_instances[pro].add(time)
        elif l.startswith(TARGET_CG):
            time = float(l.split(" ")[-2])
            target_cgs[pro].add(time)
        elif l.startswith(CLUSTER):
            time = float(l.split(" ")[-2])
            clusters[pro].add(time)
        elif l.startswith(ABSTRACT):
            time = float(l.split(" ")[-2])
            abss[pro].add(time)
        elif l.startswith(APPLY):
            time = float(l.split(" ")[-2])
            applys[pro].add(time)
# total
a = sum([sum(i) for i in all_instances.values()])/sum([len(i) for i in all_instances.values()])
t = sum([sum(i) for i in target_cgs.values()])/sum([len(i) for i in target_cgs.values()])
c = sum([sum(i) for i in clusters.values()])/sum([len(i) for i in clusters.values()])
ab = sum([sum(i) for i in abss.values()])/sum([len(i) for i in abss.values()])
ap = sum([sum(i) for i in applys.values()])/sum([len(i) for i in applys.values()])
total_instance = sum([len(i) for i in ids.values()])
print(f"{ALL_INSTANCES} {'%.2f'%a} s")
print(f"{TARGET_CG} {'%.2f'%t} s")
print(f"{CLUSTER} {'%.2f'%c} s")
print(f"{ABSTRACT} {'%.2f'%ab} s")
print(f"{APPLY} {'%.2f'%ap} s")
# each project
for pro, li in ids.items():
    aa = sum(all_instances.get(pro)) / len(all_instances.get(pro))
    tt = sum(target_cgs.get(pro)) / len(target_cgs.get(pro))
    cc = sum(clusters.get(pro)) / len(clusters.get(pro))
    abb = sum(abss.get(pro)) / len(abss.get(pro))
    app = sum(applys.get(pro)) / len(applys.get(pro))
    print(f"===== {pro} =====")
    print(f"{ALL_INSTANCES} {'%.2f'%aa} s")
    print(f"{TARGET_CG} {'%.2f'%tt} s")
    print(f"{CLUSTER} {'%.2f'%cc} s")
    print(f"{ABSTRACT} {'%.2f'%abb} s")
    print(f"{APPLY} {'%.2f'%app} s")

