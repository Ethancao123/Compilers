# 
#   Finds the max of 3 numbers
#   @author Ethan Cao
#   @version 12/2/21
#

    .data   
        msg1: .asciiz "Input an integer \n"
    .text 0x00400000
    .globl main

main:
    #prompts the user for the first input
    li $v0, 4
    la $a0, msg1
    syscall 
    li $v0, 5
    syscall
    move $t0, $v0

    #prompts the user for the first input
    li $v0, 4
    la $a0, msg1
    syscall 
    li $v0, 5
    syscall
    move $t1, $v0

    #prompts the user for the third input
    li $v0, 4
    la $a0, msg1
    syscall 
    li $v0, 5
    syscall
    move $a0, $v0

    move $a1, $t1
    move $a2, $t0
    subu $sp, $sp, 4
    sw $ra, ($sp)
    jal max3
    lw $ra, ($sp)
    addu $sp, $sp, 4
    move $a0, $v0
    #prints the result
    li $v0, 1
    syscall

end:
    li $v0, 10
    syscall

max2:
    bgt $a0, $a1, greater
    move $v0, $a1
    jr $ra
greater:
    move $v0, $a0
    jr $ra

max3: 
    subu $sp, $sp, 4
    sw $ra, ($sp)
    jal max2
    lw $ra, ($sp)
    addu $sp, $sp, 4
    move $a0, $v0
    move $a1, $a2
    subu $sp, $sp, 4
    sw $ra, ($sp)
    jal max2
    lw $ra, ($sp)
    addu $sp, $sp, 4
    jr $ra