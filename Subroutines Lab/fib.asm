# 
#   Calcualtes the sum of the fibonnaci sequence of a number
#   @author Ethan Cao
#   @version 12/6/21
#
    .data   
        msg1: .asciiz "Input the value \n"
    .text 0x00400000
    .globl main
main:
    #Prompt user
    li $v0, 4
    la $a0, msg1
    syscall

    #Read result
    li $v0, 5
    syscall

    #prints zero if the input is zero
    beq $v0, 0, isZero

    #Call fib
    move $a0, $v0
    jal fib
    move $a1, $v0

    #Print result
    li $v0, 1
    move $a0, $a1
    syscall
    li $v0, 10
    syscall


fib:
    #store previous results to the stack
    subu $sp, $sp, 12
    sw $ra, 8($sp)
    sw $t0, 4($sp)
    sw $t1, 0($sp)
    move $t0, $a0

    #return 1 if n is less than 2
    li $v0, 1
    ble $t0, 2, end

    #call fib(n-1)
    subu $a0, $t0,  1
    jal fib
    move $t1, $v0

    #call fib(n-2)
    subu $a0, $t0, 2
    jal fib
    add $v0, $t1, $v0

end:
    #empty the stack
    lw $ra, 8($sp)
    lw $t0, 4($sp)
    lw $t1, 0($sp)
    addu $sp, $sp, 12
    jr $ra

isZero:
    li $v0, 1
    la $a0, 0
    syscall