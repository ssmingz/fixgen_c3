import os

projs = {
    "Cli": [x for x in range(41) if x not in [6]],
    "Codec": [x for x in range(19)],
    "Collections": [x for x in range(1,29) if x not in range(1,25)],
    "Compress": [x for x in range(1,48)],
    "Csv": [x for x in range(1,17)],
    "Gson": [x for x in range(1,19)],
    "JacksonCore": [x for x in range(1,27)],
    "JacksonDatabind": [x for x in range(1,113)],
    "JacksonXml": [x for x in range(1,7)],
    "Jsoup": [x for x in range(1,94)],
    "JxPath": [x for x in range(1,23)],
    "Mockito": [x for x in range(1,39)]
}
for proj,ids in projs.items():
    for id in ids:
        checkout_cmd = f"defects4j checkout -p {proj} -v {id}f -w /Users/yumeng/{proj.lower()}_{id}_buggy"
        os.system(checkout_cmd)
        print(f"checkout ok {proj} {id}")



        del_cmd = f"rm -rf /Users/yumeng/{proj.lower()}_{id}_buggy"
        print(f"delete ok {proj} {id}")