	#
	#   Auto generated code created by a PASCAL compiler
	#   @author Ethan Cao
	#   @version 12/2/21
	#
	.data
	newLine: .asciiz "\n"
	VARignore: .word 0
	.text
	.globl main
main:
	subu $sp $sp 4
	sw $ra ($sp)
	# loading number
	li $v0 4
	subu $sp $sp 4
	sw $v0 ($sp)
	# binary operation
	# loading number
	li $v0 2
	subu $sp $sp 4
	sw $v0 ($sp)
	# loading number
	li $v0 3
	lw $t0 ($sp)
	addu $sp $sp 4
	addu $v0 $v0 $t0
	subu $sp $sp 4
	sw $v0 ($sp)
	# binary operation
	# loading number
	li $v0 2
	subu $sp $sp 4
	sw $v0 ($sp)
	# loading number
	li $v0 3
	lw $t0 ($sp)
	addu $sp $sp 4
	mult $v0 $t0
	mflo $v0
	subu $sp $sp 4
	sw $v0 ($sp)
	jal PROCfoo
	lw $t1 ($sp)
	addu $sp $sp 4
	lw $t1 ($sp)
	addu $sp $sp 4
	lw $t1 ($sp)
	addu $sp $sp 4
	lw $ra ($sp)
	addu $sp $sp 4
	# assigning a variable
	la $t0 VARignore
	sw $v0 0($t0)
	# ending program
	li $v0 10
	syscall
PROCfoo:
	# block of statements
	jr $ra
