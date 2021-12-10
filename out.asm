	#
	#   Auto generated code created by a PASCAL compiler
	#   @author Ethan Cao
	#   @version 12/2/21
	#
	.data
	newLine: .asciiz "\n"
	VARa: .word 0
	VARb: .word 0
	.text
	.globl main
main:
	# block of statements
	# loading number
	li $v0 9
	# assigning a variable
	la $t0 VARa
	sw $v0 0($t0)
	# loading number
	li $v0 10
	# assigning a variable
	la $t0 VARb
	sw $v0 0($t0)
	subu $sp $sp 4
	sw $ra ($sp)
	# loading number
	li $v0 11
	subu $sp $sp 4
	sw $v0 ($sp)
	# binary operation
	# evaluates a variable
	la $t0 VARa
	lw $v0 0($t0)
	subu $sp $sp 4
	sw $v0 ($sp)
	# loading number
	li $v0 3
	lw $t0 ($sp)
	addu $sp $sp 4
	addu $v0 $v0 $t0
	subu $sp $sp 4
	sw $v0 ($sp)
	jal PROCfoo
	lw $t1 ($sp)
	addu $sp $sp 4
	lw $t1 ($sp)
	addu $sp $sp 4
	lw $ra ($sp)
	addu $sp $sp 4
	# assigning a variable
	la $t0 VARa
	sw $v0 0($t0)
	# ending program
	li $v0 10
	syscall
PROCfoo:
	# block of statements
	# print statement
	# evaluates a variable
	la $t0 VARa
	lw $v0 0($t0)
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newLine
	syscall
	# print statement
	# evaluates a variable
	lw $v0 4($sp)
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newLine
	syscall
	# print statement
	# evaluates a variable
	lw $v0 0($sp)
	move $a0 $v0
	li $v0 1
	syscall
	li $v0 4
	la $a0 newLine
	syscall
	jr $ra
