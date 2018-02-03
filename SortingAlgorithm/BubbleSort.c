/*
*	Algorithm: BubbleSort
*	Author: Meghana Gudaram
*	Complexity: O(n^2)
*/

#include<stdio.h>

void BubbleSort(int *array, int n);

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
	BubbleSort(array, n);
	printf("After Sorting\n");
	for(int i=0; i < n; i++){
                printf("array[%d] = %d\n", i, array[i]);
        }
return 0;
}

void BubbleSort(int *array, int n){
	for(int i=0; i < n; i++){
		for(int j=0; j < n-1; j++){
        		if(array[j] > array[j+1]){
				int temp = array[j];
				array[j] =  array[j+1];
				array[j+1] = temp;
			}
        	}
	}
}
