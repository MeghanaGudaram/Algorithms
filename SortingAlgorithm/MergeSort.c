/*
*	Algorithm: MergeSort
*	Author: Meghana Gudaram
*	Complexity: O(nlog(n))
*/

#include<stdio.h>
#include<stdlib.h>

void MergeSort(int *array, int length);
void Merge(int *array, int *left, int *right, int leftlength, int rightlength);

int main(){
	int array[50], n=0;
	printf("Enter number of elements in array:\n");
	scanf("%d", &n);
	printf("Enter the elements:\n");
	for(int i=0; i < n; i++){
		scanf("%d", &array[i]);
	}
	printf("Before Sorting\n");
	for(int i=0; i < n; i++){
		printf("array[%d] = %d\n", i, array[i]);
	}
	MergeSort(array, n);
	printf("After Sorting\n");
	for(int i=0; i < n; i++){
                printf("array[%d] = %d\n", i, array[i]);
        }
return 0;
}

void MergeSort(int *array, int length){
	if(length < 2)
		return;
	int mid = length/2;
	int *leftarray = (int *) malloc(mid*sizeof(int));
	int *rightarray = (int *) malloc((length-mid)*sizeof(int));
	for(int i=0; i < mid; i++ ){
		leftarray[i]=array[i];
	}

	for(int i=0; i < (length-mid); i++){
        	rightarray[i]=array[mid+i];
	}

	MergeSort(leftarray, mid);
	MergeSort(rightarray, (length - mid));

	Merge(array, leftarray, rightarray, mid, (length-mid));

	free(leftarray);
	free(rightarray);
}

void Merge(int *array, int *leftarray, int *rightarray, int left, int right){
	int index=0, leftindex=0, rightindex=0;
	while(leftindex < left && rightindex < right){
		if(leftarray[leftindex] < rightarray[rightindex]){
			array[index++]=leftarray[leftindex++];
		}	 
		else{
			array[index++]=rightarray[rightindex++];
		}
	}
	while(leftindex < left){
		array[index++]=leftarray[leftindex++];
	}
	while(rightindex < right){
		array[index++]=rightarray[rightindex++];
	}
}
