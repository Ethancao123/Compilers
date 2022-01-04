	#
	#   Auto generated code created by a PASCAL compiler
	#   @author Ethan Cao
	#   @version 12/2/21
	#
	.data
	newLine: .asciiz "\n"
	VARcount: .word 0
	VARignore: .word 0
	VARtimes: .word 0
	.text
	.globl main
main:
	# block of statements
	# loading number
	li $v0 196
	# assigning a variable
	la $t0 VARcount
	sw $v0 0($t0)
	# loading number
	li $v0 0
	# assigning a variable
	la $t0 VARtimes
	sw $v0 0($t0)
	subu $sp $sp 4
	sw $ra ($sp)
	# loading number
	li $v0 11
	subu $sp $sp 4
	sw $v0 ($sp)
	# loading number
	li $v0 13
	subu $sp $sp 4
	sw $v0 ($sp)
	jal PROCprintSquares
	lw $t1 ($sp)
	addu $sp $sp 4
	lw $t1 ($sp)
	addu $sp $sp 4
	lw $ra ($sp)
	addu $sp $sp 4
	lw $v0 ($sp)
	addu $sp $sp 4
	# assigning a variable
	la $t0 VARignore
	sw $v0 0($t0)
	# print statement
	# evaluates a variable
	la $t0 VARcount
	lw $v0 0($t0)
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newLine
	syscall
	# print statement
	# evaluates a variable
	la $t0 VARtimes
	lw $v0 0($t0)
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newLine
	syscall
	# ending program
	li $v0 10
	syscall
PROCprintSquares:
	li $t2 0
	subu $sp $sp 4
	sw $t2 ($sp)
	subu $sp $sp 4
	sw $t2 ($sp)
	subu $sp $sp 4
	sw $t2 ($sp)
	# block of statements
	# evaluates a variable
	lw $v0 16($sp)
	# assigning a variable
	la $t0 VARcount
	sw $v0 0($t0)
	# while loop
	# evaluates a variable
	lw $v0 12($sp)
	move $t1 $v0
	# evaluates a variable
	la $t0 VARcount
	lw $v0 0($t0)
	bgt $v0 $t1 whileEnd0
whileBegin1:
	# block of statements
	# binary operation
	# evaluates a variable
	la $t0 VARcount
	lw $v0 0($t0)
	subu $sp $sp 4
	sw $v0 ($sp)
	# evaluates a variable
	la $t0 VARcount
	lw $v0 0($t0)
	lw $t0 ($sp)
	addu $sp $sp 4
	mult $v0 $t0
	mflo $v0
	sw $v0 4($sp)
	# print statement
	# evaluates a variable
	lw $v0 4($sp)
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newLine
	syscall
	# binary operation
	# evaluates a variable
	la $t0 VARcount
	lw $v0 0($t0)
	subu $sp $sp 4
	sw $v0 ($sp)
	# loading number
	li $v0 1
	lw $t0 ($sp)
	addu $sp $sp 4
	addu $v0 $v0 $t0
	# assigning a variable
	la $t0 VARcount
	sw $v0 0($t0)
	# binary operation
	# evaluates a variable
	la $t0 VARtimes
	lw $v0 0($t0)
	subu $sp $sp 4
	sw $v0 ($sp)
	# loading number
	li $v0 1
	lw $t0 ($sp)
	addu $sp $sp 4
	addu $v0 $v0 $t0
	# assigning a variable
	la $t0 VARtimes
	sw $v0 0($t0)
	# evaluates a variable
	lw $v0 12($sp)
	move $t1 $v0
	# evaluates a variable
	la $t0 VARcount
	lw $v0 0($t0)
	ble $v0 $t1 whileBegin1
whileEnd0:
	lw $t2 ($sp)
	addu $sp $sp 4
	lw $t2 ($sp)
	addu $sp $sp 4
	jr $ra
