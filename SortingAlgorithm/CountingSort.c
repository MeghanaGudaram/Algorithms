/*
*	Algorithm: CountingSort
*	Author: Meghana Gudaram
*	Complexity: O(n^2) and O(n)
*/
#include<stdio.h>
#include<stdlib.h>

void BigOnsquare(int *arr, int size){
	for(int i=0; i<size; i++){
		for(int j=0; j<size; j++){
			if( (i!=j) && (arr[i]==arr[j])){
				printf("BigOnsquare, Duplicate found: %d\n", arr[i]);
			}
		}
	}
}
void BigOn(int *arr, int size){
	int maxelement=0;
	for(int i=0; i<size; i++){
		if(arr[i] > maxelement)
			maxelement = arr[i];
	}
	int *count = (int *) malloc(sizeof(int) * (maxelement+1));
	for(int i=0; i<=maxelement; i++){
		count[i]=0;
	}
	for(int i=0; i<size; i++){
		count[arr[i]]++;
	}
	for(int i=0;i<maxelement; i++){
		if(count[i] > 1)
			printf("BigOn, Duplicate found: %d\n", i);
	}
}
int main(){
	int array[50], n=0;
	printf("Enter number of elements in array:\n");
	scanf("%d", &n);
	printf("Enter the elements:\n");
	for(int i=0; i < n; i++){
		scanf("%d", &array[i]);
	}
	printf("Before Searching\n");
	for(int i=0; i < n; i++){
		printf("array[%d] = %d\n", i, array[i]);
	}
	BigOnsquare(array, n);
	BigOn(array, n);
	return 0;
}
