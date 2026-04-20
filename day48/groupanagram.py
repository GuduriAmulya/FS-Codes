from collections import defaultdict
def group_anagrams(words):
    dict=defaultdict(list)
    for w in words:
        key="".join(sorted(w))
        dict[key].append(w)
    for k,v in dict.items():
        print(f"{k}-> {v}")
    return list(dict.values())

print(group_anagrams(["eat","tea","tan","ate","nat","bat"]))