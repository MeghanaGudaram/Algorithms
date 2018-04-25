#MergeSort

def merge(list1, listl, listr):
    index = 0
    lindex=0
    rindex=0
    while(lindex < len(listl) and rindex < len(listr)):
        if listl[lindex] < listr[rindex]:
            list1[index] = listl[lindex]
            index = index+1
            lindex = lindex+1
        else:
            list1[index] = listr[rindex]
            index = index+1
            rindex = rindex+1
    while lindex < len(listl):
        list1[index] = listl[lindex]
        index = index+1
        lindex = lindex+1
    while rindex < len(listr):
        list1[index] = listr[rindex]
        index = index+1
        rindex = rindex+1
        
def mergeSort(list1):
    if len(list1) <  2:
        return
    mid = len(list1)/2
    listl = list1[:mid]
    listr = list1[mid:]
    mergeSort(listl)
    mergeSort(listr)
    merge(list1, listl, listr)
list1 = [3, 5, 6, 1]

for i in list1:
    print i,
    
mergeSort(list1)
print "\n"
for i in list1:
    print i,
