	#
	#   Auto generated code created by a PASCAL compiler
	#   @author Ethan Cao
	#   @version 12/2/21
	#
	.data
	newLine: .asciiz "\n"
	.text
	.globl main
main:
	# block of statements
	# print statement
	subu $sp $sp 4
	sw $ra ($sp)
	# loading number
	li $v0 5
	subu $sp $sp 4
	sw $v0 ($sp)
	# loading number
	li $v0 12
	subu $sp $sp 4
	sw $v0 ($sp)
	jal PROCmax
	lw $t1 ($sp)
	addu $sp $sp 4
	lw $t1 ($sp)
	addu $sp $sp 4
	lw $ra ($sp)
	addu $sp $sp 4
	lw $v0 ($sp)
	addu $sp $sp 4
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newLine
	syscall
	# print statement
	subu $sp $sp 4
	sw $ra ($sp)
	# loading number
	li $v0 13
	subu $sp $sp 4
	sw $v0 ($sp)
	# loading number
	li $v0 7
	subu $sp $sp 4
	sw $v0 ($sp)
	jal PROCmax
	lw $t1 ($sp)
	addu $sp $sp 4
	lw $t1 ($sp)
	addu $sp $sp 4
	lw $ra ($sp)
	addu $sp $sp 4
	lw $v0 ($sp)
	addu $sp $sp 4
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newLine
	syscall
	# ending program
	li $v0 10
	syscall
PROCmax:
	li $t2 0
	subu $sp $sp 4
	sw $t2 ($sp)
	# block of statements
	# evaluates a variable
	lw $v0 8($sp)
	sw $v0 12($sp)
	# if statement
	# evaluates a variable
	lw $v0 8($sp)
	move $t1 $v0
	# evaluates a variable
	lw $v0 4($sp)
	ble $v0 $t1 endif0
	# evaluates a variable
	lw $v0 4($sp)
	sw $v0 12($sp)
endif0:
	jr $ra
