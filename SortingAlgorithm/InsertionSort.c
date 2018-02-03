/*
*	Algorithm: InsertionSort
*	Author: Meghana Gudaram
*	Complexity: O(n^2)
*/

#include<stdio.h>

void InsertionSort(int *array, int n);

int main(){
	int array[50], n=0;
	printf("Enter number of elements in array:\n");
	scanf("%x", &n);
	printf("Enter the elements:\n");
	for(int i=0; i < n; i++){
		scanf("%x", &array[i]);
	}
	printf("Before Sorting\n");
	for(int i=0; i < n; i++){
		printf("array[%d] = %d\n", i, array[i]);
	}
	InsertionSort(array, n);
	printf("After Sorting\n");
	for(int i=0; i < n; i++){
                printf("array[%d] = %d\n", i, array[i]);
        }
return 0;
}

void InsertionSort(int *array, int n){
	int k=n;
	for(int i=1; i < n; i++){
	int k=i-1;
		while(array[i] < array[k]){
			int temp = array[k];
			array[k] = array[i];
			array[i] = temp;
			if(k > 0)
				k--;
		}
	}
}
